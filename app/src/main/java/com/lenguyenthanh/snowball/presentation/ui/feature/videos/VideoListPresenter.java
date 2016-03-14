package com.lenguyenthanh.snowball.presentation.ui.feature.videos;

import com.lenguyenthanh.nimble.NimblePresenter;

public interface VideoListPresenter extends NimblePresenter<VideoListView> {
  void loadVideoList();

  void doRefresh();

  void playVideo();
}
