package com.lenguyenthanh.snowball.ui.network;

import java.util.Map;
import timber.log.Timber;

public interface Tracker {
  void track(String eventName);

  void track(String eventName, String... properties);

  void track(String eventName, Map<String, ?> properties);

  /**
   * Simple No-op implementation of tracking.
   */
  class SimpleTracker implements Tracker {
    @Override
    public void track(String eventName) {
      Timber.d(eventName);
    }

    @Override
    public void track(String eventName, String... properties) {
      Timber.d(eventName);
    }

    @Override
    public void track(String eventName, Map<String, ?> properties) {
      Timber.d(eventName);
    }
  }
}
