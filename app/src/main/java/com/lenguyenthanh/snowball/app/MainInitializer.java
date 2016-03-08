package com.lenguyenthanh.snowball.app;

import android.app.Application;
import com.lenguyenthanh.snowball.app.support.ActivityHierarchyServer;
import javax.inject.Inject;
import timber.log.Timber;

public class MainInitializer extends ReleaseInitializer {

  protected final Application application;
  protected final ActivityHierarchyServer activityHierarchyServer;

  @Inject
  public MainInitializer(final Application application, final Timber.Tree logTree,
      final ActivityHierarchyServer activityHierarchyServer) {
    super(logTree);
    this.application = application;
    this.activityHierarchyServer = activityHierarchyServer;
  }

  @Override
  public void initialize() {
    super.initialize();
    initializeActivityLifeCycle();
  }

  private void initializeActivityLifeCycle() {
    application.registerActivityLifecycleCallbacks(activityHierarchyServer);
  }
}
