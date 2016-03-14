package com.lenguyenthanh.snowball.presentation.ui.feature.videos.media;

import android.view.View;
import com.lenguyenthanh.snowball.presentation.model.VideoModel;
import com.lenguyenthanh.snowball.presentation.ui.feature.videos.media.holders.VideoViewHolder;
import com.lenguyenthanh.snowball.presentation.ui.network.NetworkBitmapClient;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;
import timber.log.Timber;

/**
 * Use this class if you have direct path to the video source
 */
public class DirectLinkVideoItem extends BaseVideoItem {

  private final String mDirectUrl;
  private final String mTitle;

  private final NetworkBitmapClient mImageLoader;
  private final String thumbnailUrl;

  public DirectLinkVideoItem(String title, String directUr, String thumbnailUrl,
      VideoPlayerManager videoPlayerManager, NetworkBitmapClient imageLoader) {
    super(videoPlayerManager);
    this.mDirectUrl = directUr;
    this.mTitle = title;
    this.mImageLoader = imageLoader;
    this.thumbnailUrl = thumbnailUrl;
  }

  @Override
  public void update(int position, VideoViewHolder viewHolder,
      VideoPlayerManager videoPlayerManager) {
    Timber.d("update %s", mTitle);
    viewHolder.mTitle.setText(mTitle);
    viewHolder.mCover.setVisibility(View.VISIBLE);
    mImageLoader.downloadInto(thumbnailUrl, viewHolder.mCover);
  }

  @Override
  public void playNewVideo(MetaData currentItemMetaData, VideoPlayerView player,
      VideoPlayerManager<MetaData> videoPlayerManager) {
    Timber.d("playNewVideo %s", mTitle);
    videoPlayerManager.playNewVideo(currentItemMetaData, player, mDirectUrl);
  }

  @Override
  public void stopPlayback(VideoPlayerManager videoPlayerManager) {
    videoPlayerManager.stopAnyPlayback();
  }

  public static DirectLinkVideoItem from(VideoModel videoModel, VideoPlayerManager videoPlayerManager,
      NetworkBitmapClient client) {
    return new DirectLinkVideoItem(videoModel.name(), videoModel.url(), videoModel.thumbnail(),
        videoPlayerManager, client);
  }

  @Override
  public String toString() {
    return "DirectLinkVideoItem{" +
        "mTitle='" + mTitle + '\'' +
        '}';
  }
}
