package com.lenguyenthanh.snowball;

import android.app.Activity;
import android.os.Bundle;
import com.lenguyenthanh.snowball.app.support.ActivityHierarchyServer;
import com.lenguyenthanh.snowball.models.MemoryLeakProxy;
import javax.inject.Inject;
import timber.log.Timber;

public class DebugActivityHierarchyServer implements ActivityHierarchyServer {

  private final MemoryLeakProxy leakProxy;

  @Inject
  public DebugActivityHierarchyServer(final MemoryLeakProxy leakProxy) {
    this.leakProxy = leakProxy;
  }

  @Override
  public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    Timber.d("onActivityCreated");
  }

  @Override
  public void onActivityStarted(Activity activity) {
    Timber.d("onActivityStarted");
  }

  @Override
  public void onActivityResumed(Activity activity) {
    Timber.d("onActivityResumed");
  }

  @Override
  public void onActivityPaused(Activity activity) {
    Timber.d("onActivityPaused");
  }

  @Override
  public void onActivityStopped(Activity activity) {
    Timber.d("onActivityStopped");
  }

  @Override
  public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    Timber.d("onActivitySaveInstanceState");
  }

  @Override
  public void onActivityDestroyed(Activity activity) {
    if (leakProxy != null) {
      leakProxy.watch(activity);
    } else {
      Timber.d("refWatcher == null");
    }
    Timber.d("onActivityDestroyed");
  }
}