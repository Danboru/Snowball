package com.lenguyenthanh.snowball;

import android.app.Application;
import android.support.annotation.NonNull;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lenguyenthanh.snowball.app.MainInitializer;
import com.lenguyenthanh.snowball.app.support.ActivityHierarchyServer;
import com.lenguyenthanh.snowball.models.MemoryLeakProxy;
import hu.supercluster.paperwork.Paperwork;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import timber.log.Timber;

@Singleton
public class DebugInitializer extends MainInitializer {

  private final OkHttpClient okHttpClient;
  private final Paperwork paperwork;
  private final MemoryLeakProxy memoryLeakProxy;

  @Inject
  public DebugInitializer(@NonNull final Application application,
      @NonNull final Timber.Tree logTree,
      @NonNull final ActivityHierarchyServer activityHierarchyServer,
      @NonNull final OkHttpClient okHttpClient, @NonNull final Paperwork paperwork,
      @NonNull final MemoryLeakProxy memoryLeakProxy) {
    super(application, logTree, activityHierarchyServer);
    this.okHttpClient = okHttpClient;
    this.paperwork = paperwork;
    this.memoryLeakProxy = memoryLeakProxy;
  }

  @Override
  public void initialize() {
    super.initialize();
    memoryLeakProxy.init();
    initializeStetho();
  }

  private void initializeStetho() {
    Stetho.initializeWithDefaults(application);
    this.okHttpClient.networkInterceptors().add(new StethoInterceptor());
  }
}
