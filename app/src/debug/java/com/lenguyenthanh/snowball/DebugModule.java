package com.lenguyenthanh.snowball;

import android.app.Application;
import android.support.annotation.NonNull;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lenguyenthanh.snowball.app.config.Initializer;
import com.lenguyenthanh.snowball.app.config.ActivityHierarchyServer;
import com.lenguyenthanh.snowball.data.network.OkHttpInterceptors;
import com.lenguyenthanh.snowball.data.network.OkHttpNetworkInterceptors;
import com.lenguyenthanh.snowball.models.MemoryLeakProxy;
import com.lenguyenthanh.snowball.models.MemoryLeakProxyImp;
import dagger.Module;
import dagger.Provides;
import hu.supercluster.paperwork.Paperwork;
import java.util.List;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

import static java.util.Collections.singletonList;

@Module
@Singleton
public class DebugModule {
  @Provides
  @Singleton
  MemoryLeakProxy provideLeakCanary(Application application) {
    return new MemoryLeakProxyImp(application);
  }

  @Provides
  @Singleton
  ActivityHierarchyServer provideActivityHierarchyServer(DebugActivityHierarchyServer server) {
    return server;
  }

  @Provides
  @Singleton
  Timber.Tree provideTimber() {
    return new Timber.DebugTree();
  }

  @Provides
  @Singleton
  Interceptor provideDebugInterceptor() {
    return new StethoInterceptor();
  }

  @Provides
  @Singleton
  Initializer provideInitializer(DebugInitializer debugInitializer) {
    return debugInitializer;
  }

  @Provides
  @NonNull
  @Singleton
  public Paperwork providePaperwork(@NonNull Application application) {
    return new Paperwork(application);
  }

  @Provides
  @Singleton
  @NonNull
  public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor =
        new HttpLoggingInterceptor(message -> Timber.d(message));
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return httpLoggingInterceptor;
  }

  @Provides
  @OkHttpInterceptors
  @Singleton
  @NonNull
  public List<Interceptor> provideOkHttpInterceptors(
      @NonNull HttpLoggingInterceptor httpLoggingInterceptor) {
    return singletonList(httpLoggingInterceptor);
  }

  @Provides
  @OkHttpNetworkInterceptors
  @Singleton
  @NonNull
  public List<Interceptor> provideOkHttpNetworkInterceptors() {
    return singletonList(new StethoInterceptor());
  }
}
