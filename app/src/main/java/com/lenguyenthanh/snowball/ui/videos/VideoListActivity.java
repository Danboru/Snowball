package com.lenguyenthanh.snowball.ui.videos;

import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.ui.base.BaseActivity;

public class VideoListActivity extends BaseActivity<VideoListView> implements VideoListView {

  @Override
  protected void buildComponent(SnowBallApplication.AppComponent appComponent) {

  }

  @Override
  protected VideoListPresenter presenter() {
    return null;
  }

  @Override
  protected int layoutId() {
    return 0;
  }
}
