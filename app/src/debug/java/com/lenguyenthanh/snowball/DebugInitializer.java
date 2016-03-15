package com.lenguyenthanh.snowball;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.lenguyenthanh.snowball.presentation.app.config.MainInitializer;
import com.lenguyenthanh.snowball.presentation.app.config.ActivityHierarchyServer;
import com.lenguyenthanh.snowball.models.MemoryLeakProxy;
import hu.supercluster.paperwork.Paperwork;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;

@Singleton
public class DebugInitializer extends MainInitializer {

  private final Paperwork paperwork;
  private final MemoryLeakProxy memoryLeakProxy;

  @Inject
  public DebugInitializer(final Timber.Tree logTree, final Application application,
      final ActivityHierarchyServer activityHierarchyServer, final Paperwork paperwork,
      final MemoryLeakProxy memoryLeakProxy) {
    super(logTree, application, activityHierarchyServer);
    this.paperwork = paperwork;
    this.memoryLeakProxy = memoryLeakProxy;
  }

  @Override
  public void initialize() {
    super.initialize();
    memoryLeakProxy.init();
    initializeStetho();
    Timber.d("Paper : %s %s", paperwork.get("gitSha"), paperwork.get("buildDate"));
  }

  private void initializeStetho() {
    Stetho.initializeWithDefaults(application);
  }
}
