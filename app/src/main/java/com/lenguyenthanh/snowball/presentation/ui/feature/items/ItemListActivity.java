package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.OnClick;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.presentation.model.ItemModel;
import com.lenguyenthanh.snowball.presentation.ui.base.BaseActivity;
import com.lenguyenthanh.snowball.presentation.ui.feature.items.adapter.ItemsAdapter;
import com.lenguyenthanh.snowball.presentation.ui.network.Tracker;
import com.lenguyenthanh.snowball.presentation.ui.widget.BetterViewAnimator;
import com.lenguyenthanh.snowball.presentation.ui.widget.ItemClickSupport;
import com.lenguyenthanh.snowball.presentation.ui.widget.VerticalSpaceItemDecoration;
import java.util.List;
import javax.inject.Inject;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ItemListActivity extends BaseActivity<ItemListView> implements ItemListView {

  @Inject ItemListPresenter presenter;
  @Inject Tracker tracker;
  @Bind(R.id.toolbar) Toolbar toolbar;

  // View widget
  @Bind(R.id.listVideo) RecyclerView listVideo;
  @Bind(R.id.swipeLayout) SwipeRefreshLayout swipeLayout;
  @Bind(R.id.contentLayout) BetterViewAnimator contentLayout;

  @Inject ItemsAdapter itemsAdapter;

  @OnClick(R.id.btRetry) void onBtRetryClicked() {
    presenter().loadVideoList();
    tracker.track("Retry");
  }

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeUI();
    presenter().loadVideoList();
    tracker.track("onCreate");
  }

  private void initializeUI() {
    swipeLayout.setOnRefreshListener(() -> presenter().doRefresh());
    ItemClickSupport.addTo(listVideo)
        .setOnItemClickListener((recyclerView, position, v) -> presenter.playVideo());
    toolbar.setTitle(getString(R.string.app_name));
    listVideo.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
    listVideo.addItemDecoration(new VerticalSpaceItemDecoration(
        (int) getResources().getDimension(R.dimen.list_item_vertical_space_between_items)));
    listVideo.setAdapter(itemsAdapter);
  }

  @Override protected void buildComponent(SnowBallApplication.AppComponent appComponent) {
    DaggerItemListComponent.builder()
        .itemListModule(new ItemListModule(this))
        .appComponent(appComponent)
        .build()
        .inject(this);
  }

  @Override protected ItemListPresenter presenter() {
    return presenter;
  }

  @Override protected int layoutId() {
    return R.layout.activity_item_list;
  }

  @Override public void showLoading() {
    contentLayout.setDisplayedChildId(R.id.progressBar);
  }

  private void showContent() {
    contentLayout.setDisplayedChildId(R.id.swipeLayout);
  }

  @Override public void showRetry() {
    contentLayout.setDisplayedChildId(R.id.btRetry);
  }

  @Override public void showError(final String message) {
    Snackbar.make(contentLayout, message, Snackbar.LENGTH_LONG).show();
  }

  @Override public void hideRefresh() {
    if (swipeLayout.isRefreshing()) {
      swipeLayout.setRefreshing(false);
    }
  }

  @Override public void renderVideoList(final List<ItemModel> userModelCollection) {
    showContent();
    itemsAdapter.setData(userModelCollection);
  }
}
