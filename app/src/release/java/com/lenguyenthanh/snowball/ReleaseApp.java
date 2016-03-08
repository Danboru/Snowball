package com.lenguyenthanh.snowball;

import com.lenguyenthanh.snowball.app.AppModule;
import com.lenguyenthanh.snowball.app.support.ReleaseInitializer;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.data.network.NetworkModule;
import com.lenguyenthanh.snowball.data.network.ApiModule;
import dagger.Component;
import javax.inject.Singleton;

public class ReleaseApp extends SnowBallApplication {

  @Singleton
  @Component(modules = {
      AppModule.class, NetworkModule.class, ApiModule.class, ReleaseModule.class
  })
  public interface AppComponent extends SnowBallApplication.AppComponent{
      void inject(ReleaseInitializer initializer);
  }

  @Override
  protected void initializeDaggerComponent() {
    appComponent = DaggerReleaseApp_AppComponent.builder()
        .appModule(new AppModule(this))
        .apiModule(new ApiModule(Config.BASE_URL))
        .build();
    appComponent.inject(this);
  }
}
