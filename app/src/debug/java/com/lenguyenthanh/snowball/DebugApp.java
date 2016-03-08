package com.lenguyenthanh.snowball;

import com.lenguyenthanh.snowball.app.AppModule;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.data.network.NetworkModule;
import com.lenguyenthanh.snowball.data.network.api.ApiModule;
import com.lenguyenthanh.snowball.models.MemoryLeakProxy;
import dagger.Component;
import javax.inject.Singleton;

public class DebugApp extends SnowBallApplication {
  @Singleton
  @Component(modules = {
      AppModule.class, DebugModule.class, NetworkModule.class, ApiModule.class
  })
  public interface AppComponent extends SnowBallApplication.AppComponent {
    MemoryLeakProxy memoryLeakProxy();

    void inject(DebugActivityHierarchyServer debugActivityHierarchyServer);

    void inject(DebugInitializer initializer);
  }

  @Override
  protected void initializeDaggerComponent() {
    appComponent = DaggerDebugApp_AppComponent.builder().appModule(new AppModule(this)).build();
    appComponent.inject(this);
  }

  @Override
  public AppComponent getAppComponent() {
    return ((AppComponent) appComponent);
  }
}
