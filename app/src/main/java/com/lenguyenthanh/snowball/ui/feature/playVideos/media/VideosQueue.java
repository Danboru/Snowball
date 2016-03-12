package com.lenguyenthanh.snowball.ui.feature.playVideos.media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.util.Pair;
import android.widget.VideoView;
import com.lenguyenthanh.snowball.ui.widget.BetterViewAnimator;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import timber.log.Timber;

class VideosQueue {
  private final Pair<VideoView, Integer> videoView1;
  private final Pair<VideoView, Integer> videoView2;
  private final BetterViewAnimator viewAnimator;

  private Queue<String> urls;
  private Queue<Pair<VideoView, Integer>> videoViewsQueue;

  public void setUrls(final List<String> urls) {
    this.urls = new LinkedList<>(urls);
  }

  public VideosQueue(final Pair<VideoView, Integer> videoView1,
      final Pair<VideoView, Integer> videoView2, final BetterViewAnimator viewAnimator) {
    this.videoView1 = videoView1;
    this.videoView2 = videoView2;
    this.viewAnimator = viewAnimator;
    this.videoViewsQueue = new ArrayDeque<>(2);
  }

  public void start() {
    add();
  }

  private void add() {
    Timber.d("add() %d", urls.size());
    Timber.d("videoViewsQueue %d", videoViewsQueue.size());
    if (urls.size() != 0) {
      add(urls.remove());
    }
    Timber.d("videoViewsQueue %d", videoViewsQueue.size());
  }

  private void add(String url) {
    Timber.d("add() %s", url);
    Pair<VideoView, Integer> videoView = getUnusedVideoView();
    videoViewsQueue.add(prepare(videoView, url));
  }

  private Pair<VideoView, Integer> getUnusedVideoView() {
    Pair<VideoView, Integer> videoView = videoViewsQueue.peek();
    if (videoView == null) {
      return videoView1;
    }
    if (videoView == videoView1) {
      return videoView2;
    }
    return videoView1;
  }

  private Pair<VideoView, Integer> prepare(final Pair<VideoView, Integer> videoView,
      final String url) {
    videoView.first.setVideoURI(Uri.parse(url));
    videoView.first.setOnPreparedListener(mp -> mp.setOnInfoListener((mp1, what, extra) -> {
      Timber.d("onPrepared %d %d", what, extra);
      if(what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
        add();
        videoViewsQueue.remove();
      }
      return false;
    }));
    videoView.first.setOnCompletionListener(mp -> playNextVideo());
    videoView.first.start();
    return videoView;
  }

  private void playNextVideo() {
    Timber.d("playNextVideo");
    Pair<VideoView, Integer> nextVideoView = videoViewsQueue.peek();
    if (nextVideoView != null) {
      Timber.d("playNextVideo %d", nextVideoView.second);
      viewAnimator.setDisplayedChildId(nextVideoView.second);
    }
  }
}
