package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.util.di.scope.ActivityScope;

@dagger.Component(
    dependencies = SnowBallApplication.AppComponent.class,
    modules = ItemListModule.class
)
@ActivityScope
public interface ItemListComponent {
  void inject(ItemListActivity mainActivity);
}
