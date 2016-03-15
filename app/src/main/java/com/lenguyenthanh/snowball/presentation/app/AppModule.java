package com.lenguyenthanh.snowball.presentation.app;

import android.app.Application;
import android.support.annotation.NonNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenguyenthanh.snowball.presentation.app.config.Configuration;
import com.lenguyenthanh.snowball.di.qualifier.ApiKey;
import com.lenguyenthanh.snowball.domain.executor.JobExecutor;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;
import com.lenguyenthanh.snowball.domain.executor.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
@Singleton
public class AppModule {
  Application application;

  public AppModule(Application application) {
    this.application = application;
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

  @Provides
  @Singleton
  @NonNull
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  @NonNull
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  @NonNull
  Configuration provideConfiguration() {
    return new Configuration.SimpleConfiguration();
  }

  @Provides
  @Singleton
  @NonNull
  @ApiKey
  String provideBaseUrl(Configuration configuration){
    return configuration.getBaseApiUrl();
  }

}
