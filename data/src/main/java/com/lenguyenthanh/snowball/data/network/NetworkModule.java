package com.lenguyenthanh.snowball.data.network;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {
  private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
  private static final String DISK_CACHE_FOLDER = "http";

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(Application app) {
    return createOkHttpClient(app).build();
  }

  private static OkHttpClient.Builder createOkHttpClient(Application app) {
    return new OkHttpClient.Builder().cache(createHttpCache(app));
  }

  private static Cache createHttpCache(Application application) {
    File cacheDir = new File(application.getCacheDir(), DISK_CACHE_FOLDER);
    return new Cache(cacheDir, DISK_CACHE_SIZE);
  }
}
