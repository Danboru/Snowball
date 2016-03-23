package com.lenguyenthanh.snowball.presentation.ui.base;

import android.app.Activity;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
public class ActivityModule {
  protected final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }
}
