package com.lenguyenthanh.snowball.presentation.ui.feature.playVideos.media;

import android.media.MediaCodec;
import android.net.Uri;
import android.support.v4.util.Pair;
import android.view.SurfaceView;
import com.lenguyenthanh.snowball.domain.common.Lists;
import com.lenguyenthanh.snowball.presentation.ui.widget.BetterViewAnimator;
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
  private final static int BUFFER_SEGMENT_SIZE = 64 * 1024;
  private static final int BUFFER_SEGMENT_COUNT = 256;

  private final Pair<SurfaceView, Integer> videoView1;
  private final Pair<SurfaceView, Integer> videoView2;
  private final BetterViewAnimator viewAnimator;

  private final Queue<String> urls;
  private final Queue<Pair<ExoPlayer, Integer>> videoViewsQueue;

  private PlayVideoListener playVideoListener;

  public void setPlayVideoListener(final PlayVideoListener playVideoListener) {
    this.playVideoListener = playVideoListener;
  }

  public VideosQueue(final Pair<SurfaceView, Integer> videoView1,
      final Pair<SurfaceView, Integer> videoView2, final BetterViewAnimator viewAnimator,
      final List<String> urls) {
    this.videoView1 = videoView1;
    this.videoView2 = videoView2;
    this.viewAnimator = viewAnimator;
    this.urls = new LinkedList<>(urls);;
    this.videoViewsQueue = new ArrayDeque<>(2);
  }

  public void start() {
    if (playVideoListener != null) {
      playVideoListener.isVideoStarted(false);
    }
    add();
    playNextVideo();
  }

  private void add() {
    if (!Lists.isEmptyOrNull(urls)) {
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
    if (videoView == null) {
      return videoView1;
    }
    if (videoView.second.intValue() == videoView2.second.intValue()) {
      return videoView1;
    }
    return videoView1;
  }

  private Pair<ExoPlayer, Integer> prepare(final Pair<SurfaceView, Integer> surfaceView,
      final String url) {
    ExoPlayer exoPlayer = createExoPlayer(url, surfaceView.first);
    return new Pair<>(exoPlayer, surfaceView.second);
  }

  private ExoPlayer createExoPlayer(final String url, final SurfaceView surfaceView) {
    Timber.d("createExoPlayer %s", url);

    ExtractorSampleSource sampleSource = new ExtractorSampleSource(Uri.parse(url),
        new DefaultUriDataSource(surfaceView.getContext(), "userAgent"),
        new DefaultAllocator(BUFFER_SEGMENT_SIZE), BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);
    TrackRenderer videoRenderer =
        new MediaCodecVideoTrackRenderer(surfaceView.getContext(), sampleSource,
            MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
    TrackRenderer audioRenderer =
        new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);
    TrackRenderer[] rendererArray = {videoRenderer, audioRenderer };

    final ExoPlayer exoPlayer = ExoPlayer.Factory.newInstance(rendererArray.length);

    exoPlayer.addListener(new ExoPlayer.Listener() {
      @Override
      public void onPlayerStateChanged(final boolean playWhenReady, final int playbackState) {
        Timber.d("onPlayerStateChanged %b %d", playWhenReady, playbackState);
        if (playbackState == ExoPlayer.STATE_ENDED) {
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
        // TODO: 3/15/16 throw exception
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
      if (playVideoListener != null) {
        playVideoListener.isVideoStarted(false);
      }
    }
  }

  public interface PlayVideoListener {
    void isVideoStarted(boolean isVideoStarted);
  }
}
