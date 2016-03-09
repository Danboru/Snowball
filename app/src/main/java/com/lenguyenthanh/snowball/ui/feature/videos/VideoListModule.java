package com.lenguyenthanh.snowball.ui.feature.videos;

import android.app.Activity;
import com.lenguyenthanh.snowball.data.feature.video.VideoRepositoryImpl;
import com.lenguyenthanh.snowball.di.scope.ActivityScope;
import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.video.GetVideoList;
import com.lenguyenthanh.snowball.domain.video.VideoRepository;
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

  @Provides
  @ActivityScope
  VideoRepository provideVideoRepository(VideoRepositoryImpl repository){
    return repository;
  }

  @Provides
  @ActivityScope
  UseCase provideGetVideoList(GetVideoList getVideoList){
    return getVideoList;
  }
}
