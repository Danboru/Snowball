package com.lenguyenthanh.snowball;

import com.lenguyenthanh.snowball.app.AppModule;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.data.network.NetworkModule;
import com.lenguyenthanh.snowball.data.network.ApiModule;
import com.lenguyenthanh.snowball.ui.UIModule;
import dagger.Component;
import javax.inject.Singleton;

public class DebugApp extends SnowBallApplication {
  @Singleton
  @Component(modules = {
      AppModule.class, DebugModule.class, NetworkModule.class, ApiModule.class, UIModule.class
  })
  public interface AppComponent extends SnowBallApplication.AppComponent {
    void inject(DebugActivityHierarchyServer debugActivityHierarchyServer);

    void inject(DebugInitializer initializer);
  }

  @Override
  protected void initializeDaggerComponent() {
    appComponent = DaggerDebugApp_AppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    appComponent.inject(this);
  }

  @Override
  public AppComponent getAppComponent() {
    return ((AppComponent) appComponent);
  }
}
