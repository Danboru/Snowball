package com.lenguyenthanh.snowball.ui.videos;

import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.ui.base.BaseActivity;
import javax.inject.Inject;

public class VideoListActivity extends BaseActivity<VideoListView> implements VideoListView {

  @Inject
  VideoListPresenter presenter;
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
}
