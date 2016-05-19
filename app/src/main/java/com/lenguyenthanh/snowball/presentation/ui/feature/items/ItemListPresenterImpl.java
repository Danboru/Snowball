package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import android.support.annotation.VisibleForTesting;

import com.lenguyenthanh.snowball.domain.DefaultSubscriber;
import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.exception.DefaultErrorBundle;
import com.lenguyenthanh.snowball.domain.exception.ErrorBundle;
import com.lenguyenthanh.snowball.domain.feature.item.Item;
import com.lenguyenthanh.snowball.presentation.exception.ErrorMessageFactory;
import com.lenguyenthanh.snowball.presentation.model.ItemModel;
import com.lenguyenthanh.snowball.presentation.model.ItemModelDataMapper;
import com.lenguyenthanh.snowball.presentation.ui.base.SaveStatePresenter;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import com.lenguyenthanh.snowball.util.common.Lists;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

class ItemListPresenterImpl extends SaveStatePresenter<ItemListView> implements ItemListPresenter {

  private final UseCase getItemList;
  private final ItemModelDataMapper itemModelMapper;
  private final ErrorMessageFactory errorMessageFactory;
  private final NavigationCommand navigationCommand;

  @VisibleForTesting
  public List<ItemModel> itemModels;

  @Inject
  ItemListPresenterImpl(final UseCase getItemList,
      final ItemModelDataMapper itemModelMapper, final ErrorMessageFactory errorMessageFactory,
      final NavigationCommand navigationCommand) {
    this.getItemList = getItemList;
    this.itemModelMapper = itemModelMapper;
    this.errorMessageFactory = errorMessageFactory;
    this.navigationCommand = navigationCommand;
  }

  @Override public void onDestroy() {
    getItemList.unsubscribe();
  }

  @Override public void loadItemList() {
    if (Lists.isEmptyOrNull(itemModels)) {
      getView().showLoading();
      getItemList(new LoadItemListSubscriber());
    } else {
      getView().renderItemList(itemModels);
    }
  }

  @Override public void doRefresh() {
    getItemList(new RefreshItemListSubscriber());
  }

  @Override public void gotoItemDetail() {
    navigationCommand.navigate();
  }

  private void getItemList(Subscriber<List<Item>> subscriber) {
    getItemList.execute(subscriber);
  }

  @VisibleForTesting
  public void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = errorMessageFactory.create(errorBundle.getException());
    getView().showError(errorMessage);
  }

  @VisibleForTesting
  public void showListItemInView(List<Item> items) {
    itemModels = this.itemModelMapper.transform(items);
    getView().renderItemList(itemModels);
  }

  @VisibleForTesting
  public void showError(Throwable e, boolean isRefresh){
    ItemListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle(e));
    if(isRefresh){
      getView().hideRefresh();
    }else{
      getView().showRetry();
    }
  }

  private final class LoadItemListSubscriber extends DefaultSubscriber<List<Item>> {

    @Override public void onError(Throwable e) {
      ItemListPresenterImpl.this.showError(e, false);
    }

    @Override public void onNext(List<Item> items) {
      ItemListPresenterImpl.this.showListItemInView(items);
    }
  }

  private final class RefreshItemListSubscriber extends DefaultSubscriber<List<Item>> {

    @Override public void onCompleted() {
      getView().hideRefresh();
    }

    @Override public void onError(Throwable e) {
      ItemListPresenterImpl.this.showError(e, true);
    }

    @Override public void onNext(List<Item> items) {
      ItemListPresenterImpl.this.showListItemInView(items);
    }
  }
}
