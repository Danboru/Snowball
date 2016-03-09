package com.lenguyenthanh.snowball.app;

import android.app.Application;
import android.content.Context;
import com.lenguyenthanh.snowball.app.config.Initializer;
import com.lenguyenthanh.snowball.app.config.MainInitializer;
import com.lenguyenthanh.snowball.app.config.SupportModule;
import com.lenguyenthanh.snowball.data.network.NetworkModule;
import com.lenguyenthanh.snowball.data.network.ApiModule;
import com.lenguyenthanh.snowball.ui.UIModule;
import dagger.Component;
import javax.inject.Inject;
import javax.inject.Singleton;

public class SnowBallApplication extends Application {
  @Inject
  Initializer initializer;

  protected AppComponent appComponent;

  public AppComponent getAppComponent() {
    return appComponent;
  }

  public static SnowBallApplication get(Context context) {
    return (SnowBallApplication) context.getApplicationContext();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeDaggerComponent();
    initializer.initialize();
  }

  protected void initializeDaggerComponent() {
    appComponent =
        DaggerSnowBallApplication_AppComponent.builder()
            .appModule(new AppModule(this))
            .build();
    appComponent.inject(this);
  }

  @Singleton
  @Component(modules = {
      AppModule.class, SupportModule.class, NetworkModule.class, ApiModule.class, UIModule.class
  })
  public interface AppComponent extends AppDependencies {

    void inject(SnowBallApplication app);

    void inject(MainInitializer initializer);
  }
}
