package com.lenguyenthanh.snowball.ui.feature.playVideos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.ui.feature.playVideos.media.VideosPlayer;
import java.util.Arrays;

public class PlayVideoActivity extends Activity {
  private static final String EXTRA_URLS = "EXTRA.URLS";

  public static void showMe(Activity activity, final String[] urls) {
    Intent intent = new Intent(activity, PlayVideoActivity.class);
    intent.putExtra(EXTRA_URLS, urls);
    activity.startActivity(intent);
  }

  private VideosPlayer videosPlayer;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestFullScreen();
    setContentView(R.layout.activity_play_video);
    initializeUI();
  }

  private void requestFullScreen() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  private void initializeUI() {
    videosPlayer = (VideosPlayer) findViewById(R.id.videosPlayer);
    String[] urls = getIntent().getStringArrayExtra(EXTRA_URLS);
    videosPlayer.setUrls(Arrays.asList(urls));
    videosPlayer.start();
  }
}
