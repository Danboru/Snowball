package com.lenguyenthanh.snowball.ui.feature.videos;

import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.di.scope.ActivityScope;
import com.lenguyenthanh.snowball.ui.feature.videos.media.VideoPlayerRecyclerView;

@dagger.Component(
    dependencies = SnowBallApplication.AppComponent.class,
    modules = VideoListModule.class
)
@ActivityScope
public interface VideoListComponent {
  void inject(VideoListActivity mainActivity);
  void inject(VideoPlayerRecyclerView recyclerView);
}
