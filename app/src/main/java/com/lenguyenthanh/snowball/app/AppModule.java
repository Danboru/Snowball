package com.lenguyenthanh.snowball.app;

import android.app.Application;
import android.support.annotation.NonNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenguyenthanh.snowball.data.executor.JobExecutor;
import com.lenguyenthanh.snowball.UIThread;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
@Singleton
public class AppModule {
  Application application;

  public AppModule(Application application) {
    application = application;
  }

  @Provides
  @NonNull
  @Singleton
  Application providesApplication() {
    return application;
  }

  @Provides
  @NonNull
  @Singleton
  public ObjectMapper provideObjectMapper() {
    return new ObjectMapper();
  }

  @Provides @Singleton @NonNull
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton @NonNull
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }
}
