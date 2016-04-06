package com.lenguyenthanh.snowball;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import com.lenguyenthanh.snowball.app.SnowBallApplication;

public class TestUtils {

    private TestUtils() {
        throw new IllegalStateException("No instances, please");
    }

    @NonNull
    public static SnowBallApplication app() {
        return (SnowBallApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }
}
