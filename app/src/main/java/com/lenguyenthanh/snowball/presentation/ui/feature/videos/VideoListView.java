package com.lenguyenthanh.snowball.presentation.ui.feature.videos;

import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.snowball.presentation.model.VideoModel;
import java.util.List;

public interface VideoListView extends NimbleView {

  void showLoading();

  void showRetry();

  void showError(String message);

  void hideRefresh();

  void renderVideoList(List<VideoModel> userModelCollection);
}
