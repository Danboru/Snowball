package com.lenguyenthanh.snowball.presentation.app.config;

import android.app.Application;
import javax.inject.Inject;
import timber.log.Timber;

public class MainInitializer extends ReleaseInitializer {

  protected final Application application;
  protected final ActivityHierarchyServer activityHierarchyServer;

  @Inject
  public MainInitializer(final Timber.Tree logTree,
      final Application application, final ActivityHierarchyServer activityHierarchyServer) {
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
