package com.lenguyenthanh.snowball.app.support;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.app.Initializer;
import com.lenguyenthanh.snowball.app.MainInitializer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import timber.log.Timber;

@Module
public class SupportModule {
  @Provides
  @Singleton
  @NonNull
  ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }

  @Provides
  @Singleton
  @NonNull
  Timber.Tree provideTimber() {
    return new Timber.DebugTree();
  }

  @Provides
  @Singleton
  @NonNull
  Initializer provideInitializer(MainInitializer initializer) {
    return initializer;
  }
}