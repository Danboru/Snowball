package com.lenguyenthanh.snowball.app;

import android.app.Application;
import android.content.Context;
import com.lenguyenthanh.snowball.app.support.Initializer;
import com.lenguyenthanh.snowball.app.support.MainInitializer;
import com.lenguyenthanh.snowball.app.support.SupportModule;
import com.lenguyenthanh.snowball.data.network.NetworkModule;
import com.lenguyenthanh.snowball.data.network.ApiModule;
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
            .apiModule(new ApiModule(Config.BASE_URL))
            .build();
    appComponent.inject(this);
  }

  @Singleton
  @Component(modules = {
      AppModule.class, SupportModule.class, NetworkModule.class, ApiModule.class
  })
  public interface AppComponent extends AppDependencies {

    void inject(SnowBallApplication app);

    void inject(MainInitializer initializer);
  }
}
