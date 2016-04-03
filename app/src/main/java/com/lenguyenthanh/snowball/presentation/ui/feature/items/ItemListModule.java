package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import android.app.Activity;
import android.view.LayoutInflater;
import com.lenguyenthanh.snowball.data.feature.item.ItemRepositoryImpl;
import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.feature.item.GetItemList;
import com.lenguyenthanh.snowball.domain.feature.item.ItemRepository;
import com.lenguyenthanh.snowball.presentation.ui.base.ActivityModule;
import com.lenguyenthanh.snowball.presentation.ui.feature.items.adapter.ItemsAdapter;
import com.lenguyenthanh.snowball.presentation.ui.network.SnowballImageLoader;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import com.lenguyenthanh.snowball.util.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
class ItemListModule extends ActivityModule {

  ItemListModule(Activity activity) {
    super(activity);
  }

  @Provides
  @ActivityScope
  Activity activity() {
    return this.activity;
  }

  @Provides
  @ActivityScope ItemListPresenter providePresenter(ItemListPresenterImpl presenter) {
    return presenter;
  }

  @Provides
  @ActivityScope ItemRepository provideItemRepository(ItemRepositoryImpl repository) {
    return repository;
  }

  @Provides
  @ActivityScope
  UseCase provideGetItemList(GetItemList getItemList) {
    return getItemList;
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
  ItemsAdapter provideItemsAdapter(LayoutInflater layoutInflater, SnowballImageLoader bitmapClient){
    return new ItemsAdapter(layoutInflater, bitmapClient);
  }
}
