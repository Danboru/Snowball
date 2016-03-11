package com.lenguyenthanh.snowball.ui.feature.videos;

import android.app.Activity;
import com.lenguyenthanh.snowball.ui.feature.playVideos.PlayVideoActivity;
import com.lenguyenthanh.snowball.util.ui.NavigationCommand;

public class PlayVideoNavigationCommand implements NavigationCommand {
  private Activity activity;
  private String [] urls;

  public void setUrls(final String[] urls) {
    this.urls = urls;
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
