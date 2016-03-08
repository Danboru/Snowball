package com.lenguyenthanh.snowball.ui.videos;

import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.snowball.model.VideoModel;
import java.util.Collection;

public interface VideoListView extends NimbleView {

  void showLoading();

  void hideLoading();

  void showRetry();

  void hideRetry();

  void showError(String message);

  void renderVideoList(Collection<VideoModel> userModelCollection);
}
