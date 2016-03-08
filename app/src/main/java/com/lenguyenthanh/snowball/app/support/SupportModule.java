package com.lenguyenthanh.snowball.app.support;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.app.Initializer;
import com.lenguyenthanh.snowball.app.MainInitializer;
import com.lenguyenthanh.snowball.data.network.OkHttpInterceptors;
import com.lenguyenthanh.snowball.data.network.OkHttpNetworkInterceptors;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import timber.log.Timber;

import static java.util.Collections.emptyList;

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

  @Provides @OkHttpInterceptors
  @Singleton @NonNull
  public List<Interceptor> provideOkHttpInterceptors() {
    return emptyList();
  }

  @Provides @OkHttpNetworkInterceptors
  @Singleton @NonNull
  public List<Interceptor> provideOkHttpNetworkInterceptors() {
    return emptyList();
  }
}