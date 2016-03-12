package com.lenguyenthanh.snowball.ui.feature.videos.media;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.ui.feature.videos.media.holders.VideoViewHolder;
import com.volokh.danylo.video_player_manager.manager.VideoItem;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.CurrentItemMetaData;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper;
import com.volokh.danylo.video_player_manager.utils.Logger;
import com.volokh.danylo.visibility_utils.items.ListItem;
import timber.log.Timber;

public abstract class BaseVideoItem implements VideoItem, ListItem {

  private static final boolean SHOW_LOGS = false;
  private static final String TAG = BaseVideoItem.class.getSimpleName();

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
   * 2. {@link android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder,
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
        new MediaPlayerWrapper.MainThreadMediaPlayerListener() {
          int count = 0;
          @Override
          public void onVideoSizeChangedMainThread(int width, int height) {
            Timber.d("----onVideoSizeChangedMainThread %d %d", width, height);
          }

          @Override
          public void onVideoPreparedMainThread() {
            // When video is prepared it's about to start playback. So we hide the cover
            videoViewHolder.mCover.setVisibility(View.INVISIBLE);
            Timber.d("----onVideoPreparedMainThread %d", count++);
          }

          @Override
          public void onVideoCompletionMainThread() {
            videoViewHolder.mCover.setVisibility(View.VISIBLE);
          }

          @Override
          public void onErrorMainThread(int what, int extra) {
            Timber.d("----onErrorMainThread %d %d", what, extra);
          }

          @Override
          public void onBufferingUpdateMainThread(int percent) {
            Timber.d("----onBufferingUpdateMainThread %d", percent);
          }

          @Override
          public void onVideoStoppedMainThread() {
            // Show the cover when video stopped
            videoViewHolder.mCover.setVisibility(View.VISIBLE);
            Timber.d("----onVideoStoppedMainThread");
          }
        });
    return view;
  }

  /**
   * This method calculates visibility percentage of currentView.
   * This method works correctly when currentView is smaller then it's enclosure.
   *
   * @param currentView - view which visibility should be calculated
   * @return currentView visibility percents
   */
  @Override
  public int getVisibilityPercents(View currentView) {
    if (SHOW_LOGS) Logger.v(TAG, ">> getVisibilityPercents currentView " + currentView);

    int percents = 100;

    currentView.getLocalVisibleRect(mCurrentViewRect);
    if (SHOW_LOGS) {
      Logger.v(TAG, "getVisibilityPercents mCurrentViewRect top " + mCurrentViewRect.top + ", left "
              + mCurrentViewRect.left + ", bottom " + mCurrentViewRect.bottom + ", right "
              + mCurrentViewRect.right);
    }

    int height = currentView.getHeight();
    if (SHOW_LOGS) Logger.v(TAG, "getVisibilityPercents height " + height);

    if (viewIsPartiallyHiddenTop()) {
      // view is partially hidden behind the top edge
      percents = (height - mCurrentViewRect.top) * 100 / height;
    } else if (viewIsPartiallyHiddenBottom(height)) {
      percents = mCurrentViewRect.bottom * 100 / height;
    }

    //setVisibilityPercentsText(currentView, percents);
    if (SHOW_LOGS) Logger.v(TAG, "<< getVisibilityPercents, percents " + percents);

    return percents;
  }

  private boolean viewIsPartiallyHiddenBottom(int height) {
    return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
  }

  private boolean viewIsPartiallyHiddenTop() {
    return mCurrentViewRect.top > 0;
  }
}
