package com.lenguyenthanh.snowball.presentation.ui.feature.videos;

import android.app.Activity;
import android.view.LayoutInflater;
import com.lenguyenthanh.snowball.data.feature.video.VideoRepositoryImpl;
import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.feature.video.GetVideoList;
import com.lenguyenthanh.snowball.domain.feature.video.VideoRepository;
import com.lenguyenthanh.snowball.presentation.ui.base.ActivityModule;
import com.lenguyenthanh.snowball.presentation.ui.feature.videos.adapter.ItemsAdapter;
import com.lenguyenthanh.snowball.presentation.ui.network.NetworkBitmapClient;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import com.lenguyenthanh.snowball.util.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class VideoListModule extends ActivityModule {

  public VideoListModule(Activity activity) {
    super(activity);
  }

  @Provides
  @ActivityScope
  Activity activity() {
    return this.activity;
  }

  @Provides
  @ActivityScope
  VideoListPresenter providePresenter(VideoListPresenterImpl presenter) {
    return presenter;
  }

  @Provides
  @ActivityScope
  VideoRepository provideVideoRepository(VideoRepositoryImpl repository) {
    return repository;
  }

  @Provides
  @ActivityScope
  UseCase provideGetVideoList(GetVideoList getVideoList) {
    return getVideoList;
  }

  @Provides
  @ActivityScope
  NavigationCommand provideNavigationCommand() {
    return new NoWhereCommand();
  }

  @Provides
  @ActivityScope
  LayoutInflater provideInflater(){
    return this.activity.getLayoutInflater();
  }

  @Provides
  @ActivityScope
  ItemsAdapter provideItemsAdapter(LayoutInflater layoutInflater, NetworkBitmapClient bitmapClient){
    return new ItemsAdapter(layoutInflater, bitmapClient);
  }
}
