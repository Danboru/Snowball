package com.lenguyenthanh.snowball;

import android.util.Log;
import com.lenguyenthanh.snowball.app.Initializer;
import com.lenguyenthanh.snowball.app.ReleaseInitializer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import timber.log.Timber;

@Module
@Singleton
public class ReleaseModule{

  @Provides
  @Singleton
  Timber.Tree provideTimber() {
    return new Timber.Tree() {

      @Override
      protected void log(int priority, String tag, String message, Throwable t) {
        Log.wtf(tag, "Release");
      }
    };
  }

  @Provides
  @Singleton
  Initializer provideApplicationInit(ReleaseInitializer initializer){
    return initializer;
  }
}
