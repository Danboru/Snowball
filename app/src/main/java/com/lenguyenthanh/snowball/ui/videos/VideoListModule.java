package com.lenguyenthanh.snowball.ui.videos;

import android.app.Activity;
import com.lenguyenthanh.snowball.di.scope.ActivityScope;
import com.lenguyenthanh.snowball.ui.base.ActivityModule;
import dagger.Module;
import dagger.Provides;

@Module
public class VideoListModule extends ActivityModule {

  public VideoListModule(Activity activity) {
    super(activity);
  }

  @Provides
  @ActivityScope
  VideoListPresenter providePresenter(VideoListPresenterImpl presenter) {
    return presenter;
  }

  @Provides
  @ActivityScope
  Activity activity() {
    return this.activity;
  }
}
