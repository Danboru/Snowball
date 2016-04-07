package com.lenguyenthanh.snowball.test;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.TestUtils;
import com.lenguyenthanh.snowball.presentation.ui.feature.items.ItemListActivity;
import com.lenguyenthanh.snowball.screen.ItemsScreen;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ItemsTest {

    @Rule
    public RuleChain rules = RuleChain.emptyRuleChain()
            .around(new ActivityTestRule<>(ItemListActivity.class));

    @SuppressWarnings("NullableProblems") // Initialized in @Before.
    @NonNull
    private ItemsScreen itemsScreen;

    @Before
    public void beforeEachTest() {
        itemsScreen = new ItemsScreen();
    }

    @Test // For real, it's really easy to break Toolbar, so why not test it?
    public void shouldDisplayTitle() {
        itemsScreen.shouldDisplayTitle(TestUtils.app().getString(R.string.app_name));
    }
}
