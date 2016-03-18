package com.lenguyenthanh.snowball;

import com.lenguyenthanh.snowball.app.AppModule;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.app.config.ReleaseInitializer;
import com.lenguyenthanh.snowball.data.network.ApiModule;
import com.lenguyenthanh.snowball.data.network.NetworkModule;
import com.lenguyenthanh.snowball.presentation.ui.UIModule;
import dagger.Component;
import javax.inject.Singleton;

public class ReleaseApp extends SnowBallApplication {

  @Singleton
  @Component(modules = {
      AppModule.class, NetworkModule.class, ApiModule.class, ReleaseModule.class, UIModule.class
  })
  public interface AppComponent extends SnowBallApplication.AppComponent{
      void inject(ReleaseInitializer initializer);
  }

  @Override
  protected void initializeDaggerComponent() {
    appComponent = DaggerReleaseApp_AppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    appComponent.inject(this);
  }
}
