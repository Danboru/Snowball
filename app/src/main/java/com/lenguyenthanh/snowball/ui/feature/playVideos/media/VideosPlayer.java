package com.lenguyenthanh.snowball.ui.feature.playVideos.media;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.VideoView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.ui.widget.BetterViewAnimator;
import java.util.List;

public class VideosPlayer extends FrameLayout {

  @Bind(R.id.contentLayout)
  BetterViewAnimator viewAnimator;
  @Bind(R.id.videoView1)
  VideoView videoView1;
  @Bind(R.id.videoView2)
  VideoView videoView2;

  private VideosQueue queue;

  public void start() {
    queue.start();
  }

  public void setUrls(final List<String> urls) {
    queue.setUrls(urls);
  }

  public VideosPlayer(final Context context, final AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  private void initialize() {
    LayoutInflater.from(getContext()).inflate(R.layout.view_video_player, this);
    ButterKnife.bind(this);
    queue = new VideosQueue(new Pair<>(videoView1, R.id.videoView1),
        new Pair<>(videoView2, R.id.videoView2), viewAnimator);

  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    ButterKnife.unbind(this);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    ButterKnife.bind(this);
  }
}
