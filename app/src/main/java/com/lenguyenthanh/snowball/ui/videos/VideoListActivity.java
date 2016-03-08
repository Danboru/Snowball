package com.lenguyenthanh.snowball.ui.videos;

import android.os.Bundle;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.model.VideoModel;
import com.lenguyenthanh.snowball.ui.base.BaseActivity;
import java.util.Collection;
import javax.inject.Inject;

public class VideoListActivity extends BaseActivity<VideoListView> implements VideoListView {

  @Inject
  VideoListPresenter presenter;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter().loadVideoList();
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

  }

  @Override
  public void hideLoading() {

  }

  @Override
  public void showRetry() {

  }

  @Override
  public void hideRetry() {

  }

  @Override
  public void showError(final String message) {

  }

  @Override
  public void renderVideoList(final Collection<VideoModel> userModelCollection) {

  }
}
