package com.lenguyenthanh.snowball.ui.feature.videos;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.OnClick;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.model.VideoModel;
import com.lenguyenthanh.snowball.ui.base.BaseActivity;
import com.lenguyenthanh.snowball.ui.network.Tracker;
import com.lenguyenthanh.snowball.ui.widget.BetterViewAnimator;
import java.util.Collection;
import javax.inject.Inject;

public class VideoListActivity extends BaseActivity<VideoListView> implements VideoListView {

  @Inject
  VideoListPresenter presenter;
  @Inject
  Tracker tracker;

  // View widget
  @Bind(R.id.listVideo)
  RecyclerView listVideo;
  @Bind(R.id.swipeLayout)
  SwipeRefreshLayout swipeLayout;
  @Bind(R.id.contentLayout)
  BetterViewAnimator contentLayout;

  @OnClick(R.id.btRetry)
  void onBtRetryClicked(){
    presenter().loadVideoList();
    tracker.track("Retry");
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeUI();
    presenter().loadVideoList();
    tracker.track("onCreate");
  }

  private void initializeUI(){
    swipeLayout.setOnRefreshListener(() -> presenter().doRefresh());
  }

  @Override
  protected void buildComponent(SnowBallApplication.AppComponent appComponent) {
    VideoListComponent component = DaggerVideoListComponent.builder()
        .videoListModule(new VideoListModule(this))
        .appComponent(appComponent)
        .build();
    component.inject(this);
  }

  @Override
  protected VideoListPresenter presenter() {
    return presenter;
  }

  @Override
  protected int layoutId() {
    return R.layout.activity_video_list;
  }

  @Override
  public void showLoading() {
    contentLayout.setDisplayedChildId(R.id.progressBar);
  }

  private void showContent() {
    contentLayout.setDisplayedChildId(R.id.swipeLayout);
  }

  @Override
  public void showRetry() {
    contentLayout.setDisplayedChildId(R.id.btRetry);
  }

  @Override
  public void showError(final String message) {
    Snackbar.make(contentLayout, message, Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void hideRefresh() {
    if(swipeLayout.isRefreshing()){
      swipeLayout.setRefreshing(false);
    }
  }

  @Override
  public void renderVideoList(final Collection<VideoModel> userModelCollection) {
    showContent();
  }
}
