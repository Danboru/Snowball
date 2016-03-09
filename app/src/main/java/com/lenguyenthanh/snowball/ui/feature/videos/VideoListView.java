package com.lenguyenthanh.snowball.ui.feature.videos;

import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.snowball.model.VideoModel;
import java.util.Collection;

public interface VideoListView extends NimbleView {

  void showLoading();

  void showRetry();

  void showError(String message);

  void hideRefresh();

  void renderVideoList(Collection<VideoModel> userModelCollection);
}
