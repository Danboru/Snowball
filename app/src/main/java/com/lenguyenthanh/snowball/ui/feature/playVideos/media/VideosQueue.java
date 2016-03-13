package com.lenguyenthanh.snowball.ui.feature.playVideos.media;

import android.media.MediaCodec;
import android.net.Uri;
import android.support.v4.util.Pair;
import android.view.SurfaceView;
import com.lenguyenthanh.snowball.ui.widget.BetterViewAnimator;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import timber.log.Timber;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;

public class VideosQueue {
  private final Pair<SurfaceView, Integer> videoView1;
  private final Pair<SurfaceView, Integer> videoView2;
  private final BetterViewAnimator viewAnimator;

  private Queue<String> urls;
  private Queue<Pair<ExoPlayer, Integer>> videoViewsQueue;

  private PlayVideoListener playVideoListener;

  public void setPlayVideoListener(final PlayVideoListener playVideoListener) {
    this.playVideoListener = playVideoListener;
  }

  public void setUrls(final List<String> urls) {
    this.urls = new LinkedList<>(urls);
  }

  public VideosQueue(final Pair<SurfaceView, Integer> videoView1,
      final Pair<SurfaceView, Integer> videoView2, final BetterViewAnimator viewAnimator) {
    this.videoView1 = videoView1;
    this.videoView2 = videoView2;
    this.viewAnimator = viewAnimator;
    this.videoViewsQueue = new ArrayDeque<>(2);
  }

  public void start() {
    if(playVideoListener != null){
      playVideoListener.isVideoStarted(false);
    }
    add();
    playNextVideo();
  }

  private void add() {
    Timber.d("add() %d", urls.size());
    if (urls.size() != 0) {
      add(urls.remove());
    }
  }

  private void add(String url) {
    Timber.d("add() %s", url);
    Pair<SurfaceView, Integer> surfaceView = getUnusedVideoView();
    videoViewsQueue.add(prepare(surfaceView, url));
  }

  private Pair<SurfaceView, Integer> getUnusedVideoView() {
    Pair<ExoPlayer, Integer> videoView = videoViewsQueue.peek();
    //if (videoView == null) {
    //  return videoView1;
    //}
    //if (videoView.second.intValue() == videoView1.second.intValue()) {
    //  return videoView2;
    //}
    return videoView1;
  }

  private Pair<ExoPlayer, Integer> prepare(final Pair<SurfaceView, Integer> surfaceView,
      final String url) {
      ExoPlayer exoPlayer = createExoPlayer(url, surfaceView.first);
    return new Pair<>(exoPlayer, surfaceView.second);
  }

  private ExoPlayer createExoPlayer(final String url, final SurfaceView surfaceView) {
    Timber.d("createExoPlayer %s", url);
    final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    final int BUFFER_SEGMENT_COUNT = 256;

    ExtractorSampleSource sampleSource =
        new ExtractorSampleSource(Uri.parse(url), new DefaultUriDataSource(surfaceView.getContext(), "userAgent"),
            new DefaultAllocator(BUFFER_SEGMENT_SIZE), BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);
    TrackRenderer videoRenderer =
        new MediaCodecVideoTrackRenderer(surfaceView.getContext(), sampleSource, MediaCodecSelector.DEFAULT,
            MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
    TrackRenderer audioRenderer =
        new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);
    TrackRenderer[] rendererArray = { videoRenderer, audioRenderer };

    final ExoPlayer exoPlayer = ExoPlayer.Factory.newInstance(rendererArray.length);


    exoPlayer.addListener(new ExoPlayer.Listener() {
      @Override
      public void onPlayerStateChanged(final boolean playWhenReady, final int playbackState) {
        Timber.d("onPlayerStateChanged %b %d", playWhenReady, playbackState);
        if(playbackState == ExoPlayer.STATE_ENDED){
          exoPlayer.stop();
          exoPlayer.seekTo(0);
          add();
          playNextVideo();
        }
        //if(playbackState == ExoPlayer.STATE_READY){
        //  add();
        //}
      }

      @Override
      public void onPlayWhenReadyCommitted() {
        Timber.d("onPlayWhenReadyCommitted");
      }

      @Override
      public void onPlayerError(final ExoPlaybackException error) {

      }
    });
    exoPlayer.prepare(rendererArray);
    exoPlayer.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,
        surfaceView.getHolder().getSurface());
    exoPlayer.setPlayWhenReady(true);
    return exoPlayer;
  }

  private void playNextVideo() {
    Timber.d("playNextVideo");
    Pair<ExoPlayer, Integer> nextVideoView = videoViewsQueue.peek();
    if (nextVideoView != null) {
      viewAnimator.setDisplayedChildId(nextVideoView.second);
      nextVideoView.first.setPlayWhenReady(true);
      if(playVideoListener != null){
        playVideoListener.isVideoStarted(false);
      }
    }
  }

  public interface PlayVideoListener{
    void isVideoStarted(boolean isVideoStarted);
  }
}
