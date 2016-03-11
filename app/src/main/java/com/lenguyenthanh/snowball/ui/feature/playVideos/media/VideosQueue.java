package com.lenguyenthanh.snowball.ui.feature.playVideos.media;

import android.support.v4.util.Pair;
import android.widget.VideoView;
import com.lenguyenthanh.snowball.ui.widget.BetterViewAnimator;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    add();
    videoViewsQueue.remove().first.start();
  }

  private void add(String url) {
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
    videoView.first.setVideoPath(url);
    //videoView.first.start();
    //videoView.first.setOnPreparedListener(MediaPlayer::pause);
    videoView.first.setOnCompletionListener(mp -> playNextVideo());
    return videoView;
  }

  private void playNextVideo() {
    Pair<VideoView, Integer> nextVideoView = videoViewsQueue.poll();
    if(nextVideoView != null) {
      viewAnimator.setDisplayedChildId(nextVideoView.second);
      nextVideoView.first.start();
      add();
    }
  }

  private void add() {
    if (urls.size() != 0) {
      add(urls.remove());
    }
  }
}
