package com.lenguyenthanh.snowball.app.config;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.data.network.OkHttpInterceptors;
import com.lenguyenthanh.snowball.data.network.OkHttpNetworkInterceptors;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import timber.log.Timber;

import static java.util.Collections.emptyList;

@Module public class SupportModule {
  @Provides @Singleton @NonNull public ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }

  @Provides @Singleton @NonNull public Timber.Tree provideTimber() {
    return new Timber.DebugTree();
  }

  @Provides @Singleton @NonNull public Initializer provideInitializer(MainInitializer initializer) {
    return initializer;
  }

  @Provides @OkHttpInterceptors @Singleton @NonNull
  public List<Interceptor> provideOkHttpInterceptors() {
    return emptyList();
  }

  @Provides @OkHttpNetworkInterceptors @Singleton @NonNull
  public List<Interceptor> provideOkHttpNetworkInterceptors() {
    return emptyList();
  }

  @Provides @Singleton @NonNull public Configuration provideConfiguration() {
    return new Configuration.SimpleConfiguration();
  }
}