package com.lenguyenthanh.snowball.app;

import android.app.Application;
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
  @Singleton
  Application providesApplication() {
    return application;
  }
}
