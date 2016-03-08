package com.lenguyenthanh.snowball.ui.videos;

import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.di.scope.ActivityScope;

@dagger.Component(
    dependencies = SnowBallApplication.AppComponent.class,
    modules = VideoListModule.class
)
@ActivityScope
public interface VideoListComponent {
  void inject(VideoListActivity mainActivity);
}
