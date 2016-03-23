package com.lenguyenthanh.snowball.presentation.ui.feature.videos;

import android.app.Activity;
import com.lenguyenthanh.snowball.presentation.ui.feature.playVideos.PlayVideoActivity;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import java.util.Arrays;

public class PlayVideoNavigationCommand implements NavigationCommand {
  private final Activity activity;
  private String [] urls;

  public void setUrls(final String... urls) {
    this.urls = Arrays.copyOf(urls, urls.length);
  }

  public PlayVideoNavigationCommand(final Activity activity) {
    this.activity = activity;
  }

  @Override
  public void navigate() {
    if (urls == null) {
      throw new NumberFormatException("Urls should not null.");
    }
    PlayVideoActivity.showMe(activity, urls);
  }
}
