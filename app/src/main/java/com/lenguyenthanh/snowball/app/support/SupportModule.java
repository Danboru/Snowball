package com.lenguyenthanh.snowball.app.support;

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
  ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }

  @Provides
  @Singleton
  Timber.Tree provideTimber() {
    return new Timber.DebugTree();
  }

  @Provides
  @Singleton
  Initializer provideInitializer(MainInitializer initializer){
    return initializer;
  }
}