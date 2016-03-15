package com.lenguyenthanh.snowball.presentation.ui.feature.videos.media;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.presentation.ui.feature.videos.media.holders.VideoViewHolder;
import com.volokh.danylo.video_player_manager.manager.VideoItem;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.CurrentItemMetaData;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper;
import com.volokh.danylo.visibility_utils.items.ListItem;
import timber.log.Timber;

public abstract class BaseVideoItem implements VideoItem, ListItem {

  /**
   * An object that is filled with values when {@link #getVisibilityPercents} method is called.
   * This object is local visible rect filled by {@link View#getLocalVisibleRect}
   */

  private final Rect mCurrentViewRect = new Rect();
  private final VideoPlayerManager<MetaData> mVideoPlayerManager;

  protected BaseVideoItem(VideoPlayerManager<MetaData> videoPlayerManager) {
    mVideoPlayerManager = videoPlayerManager;
  }

  /**
   * This method needs to be called when created/recycled view is updated.
   * Call it in
   * 1. {@link android.widget.ListAdapter#getView(int, View, ViewGroup)}
   * 2. {@link android.support.v7.widget.RecyclerView.Adapter#,
   * int)}
   */
  public abstract void update(int position, VideoViewHolder view,
      VideoPlayerManager videoPlayerManager);

  /**
   * When this item becomes active we start playback on the video in this item
   */
  @Override
  public void setActive(View newActiveView, int newActiveViewPosition) {
    VideoViewHolder viewHolder = (VideoViewHolder) newActiveView.getTag();
    playNewVideo(new CurrentItemMetaData(newActiveViewPosition, newActiveView), viewHolder.mPlayer,
        mVideoPlayerManager);
  }

  /**
   * When this item becomes inactive we stop playback on the video in this item.
   */
  @Override
  public void deactivate(View currentView, int position) {
    stopPlayback(mVideoPlayerManager);
  }

  public View createView(ViewGroup parent, int screenWidth) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    layoutParams.height = screenWidth;

    final VideoViewHolder videoViewHolder = new VideoViewHolder(view);
    view.setTag(videoViewHolder);

    videoViewHolder.mPlayer.addMediaPlayerListener(
        new MyMainThreadMediaPlayerListener(videoViewHolder));
    return view;
  }

  /**
   * This method calculates visibility percentage of currentView.
   * This method works correctly when currentView is smaller then it's enclosure.
   *
   * @param currentView - view which visibility should be calculated
   * @return currentView visibility percentsÒ
   */
  @Override
  public int getVisibilityPercents(View currentView) {
    Timber.d(">> getVisibilityPercents currentView %s", currentView);

    int percents = 100;

    currentView.getLocalVisibleRect(mCurrentViewRect);
    Timber.d(
        "getVisibilityPercents mCurrentViewRect top %d, left %d, bottom %d, right %d", mCurrentViewRect.top, mCurrentViewRect.left, mCurrentViewRect.bottom, mCurrentViewRect.right);

    int height = currentView.getHeight();
    Timber.d("getVisibilityPercents height %d", height);

    if (viewIsPartiallyHiddenTop()) {
      // view is partially hidden behind the top edge
      percents = (height - mCurrentViewRect.top) * 100 / height;
    } else if (viewIsPartiallyHiddenBottom(height)) {
      percents = mCurrentViewRect.bottom * 100 / height;
    }

    //setVisibilityPercentsText(currentView, percents);
    Timber.d("<< getVisibilityPercents, percents %d", percents);

    return percents;
  }

  private boolean viewIsPartiallyHiddenBottom(int height) {
    return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
  }

  private boolean viewIsPartiallyHiddenTop() {
    return mCurrentViewRect.top > 0;
  }

  private static class MyMainThreadMediaPlayerListener
      implements MediaPlayerWrapper.MainThreadMediaPlayerListener {

    private final VideoViewHolder videoViewHolder;

    public MyMainThreadMediaPlayerListener(final VideoViewHolder videoViewHolder) {
      this.videoViewHolder = videoViewHolder;
    }

    @Override
    public void onVideoSizeChangedMainThread(int width, int height) {
      Timber.d("----onVideoSizeChangedMainThread %d %d", width, height);
    }

    @Override
    public void onVideoPreparedMainThread() {
      videoViewHolder.mVisibilityPercents.setText(R.string.video_is_loading);
      videoViewHolder.mVisibilityPercents.setVisibility(View.VISIBLE);
    }

    @Override
    public void onVideoCompletionMainThread() {
      videoViewHolder.mCover.setVisibility(View.VISIBLE);
      videoViewHolder.mVisibilityPercents.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorMainThread(int what, int extra) {
      Timber.d("----onErrorMainThread %d %d", what, extra);
    }

    @Override
    public void onBufferingUpdateMainThread(int percent) {
      Timber.d("----onBufferingUpdateMainThread %d", percent);
      videoViewHolder.mVisibilityPercents.setText(
          String.format("Loading %d percent", percent));
    }

    @Override
    public void onVideoStoppedMainThread() {
      // Show the cover when video stopped
      videoViewHolder.mCover.setVisibility(View.VISIBLE);
      videoViewHolder.mVisibilityPercents.setVisibility(View.INVISIBLE);
      Timber.d("----onVideoStoppedMainThread");
    }

    @Override
    public void onVideoStartedMainThread() {
      videoViewHolder.mCover.setVisibility(View.INVISIBLE);
      videoViewHolder.mVisibilityPercents.setVisibility(View.INVISIBLE);
    }
  }
}
