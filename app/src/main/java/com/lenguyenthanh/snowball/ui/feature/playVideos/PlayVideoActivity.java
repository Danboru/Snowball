package com.lenguyenthanh.snowball.ui.feature.playVideos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.ui.feature.playVideos.media.VideosPlayer;
import java.util.Arrays;

public class PlayVideoActivity extends Activity {
  private static final String EXTRA_URLS = "EXTRA.URLS";
  @Bind(R.id.progressBar)
  ProgressBar progressBar;

  public static void showMe(Activity activity, final String[] urls) {
    Intent intent = new Intent(activity, PlayVideoActivity.class);
    intent.putExtra(EXTRA_URLS, urls);
    activity.startActivity(intent);
  }

  @Bind(R.id.videosPlayer)
  VideosPlayer videosPlayer;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestFullScreen();
    setContentView(R.layout.activity_play_video);
    ButterKnife.bind(this);
    initializeUI();
  }

  private void requestFullScreen() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  private void initializeUI() {
    String[] urls = getIntent().getStringArrayExtra(EXTRA_URLS);
    videosPlayer.setPlayVideoListener(isVideoStarted -> {
      if(isVideoStarted){
        progressBar.setVisibility(View.INVISIBLE);
      }else{
        progressBar.setVisibility(View.VISIBLE);
      }
    });
    videosPlayer.setUrls(Arrays.asList(urls));
    videosPlayer.start();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
