package com.lenguyenthanh.snowball.presentation.ui.feature.items;

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

  private final UseCase getVideoList;
  private final ItemModelDataMapper videoModelMapper;
  private final ErrorMessageFactory errorMessageFactory;
  private final NavigationCommand navigationCommand;

  private List<ItemModel> itemModels;

  @Inject ItemListPresenterImpl(final UseCase getVideoList,
      final ItemModelDataMapper videoModelMapper, final ErrorMessageFactory errorMessageFactory,
      final NavigationCommand navigationCommand) {
    this.getVideoList = getVideoList;
    this.videoModelMapper = videoModelMapper;
    this.errorMessageFactory = errorMessageFactory;
    this.navigationCommand = navigationCommand;
  }

  @Override public void onDestroy() {
    getVideoList.unsubscribe();
  }

  @Override public void loadVideoList() {
    if (Lists.isEmptyOrNull(itemModels)) {
      getView().showLoading();
      getVideoList(new LoadVideoListSubscriber());
    } else {
      getView().renderVideoList(itemModels);
    }
  }

  @Override public void doRefresh() {
    getVideoList(new RefreshVideoListSubscriber());
  }

  @Override public void playVideo() {
    navigationCommand.navigate();
  }

  private void getVideoList(Subscriber<List<Item>> subscriber) {
    getVideoList.execute(subscriber);
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = errorMessageFactory.create(errorBundle.getException());
    getView().showError(errorMessage);
  }

  private void showVideoCollectionInView(List<Item> usersCollection) {
    itemModels = this.videoModelMapper.transform(usersCollection);
    getView().renderVideoList(itemModels);
  }

  private final class LoadVideoListSubscriber extends DefaultSubscriber<List<Item>> {

    @Override public void onError(Throwable e) {
      if (e instanceof Exception) {
        ItemListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      }
      getView().showRetry();
    }

    @Override public void onNext(List<Item> items) {
      ItemListPresenterImpl.this.showVideoCollectionInView(items);
    }
  }

  private final class RefreshVideoListSubscriber extends DefaultSubscriber<List<Item>> {

    @Override public void onCompleted() {
      getView().hideRefresh();
    }

    @Override public void onError(Throwable e) {
      if (e instanceof Exception) {
        ItemListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      }
      getView().hideRefresh();
    }

    @Override public void onNext(List<Item> items) {
      ItemListPresenterImpl.this.showVideoCollectionInView(items);
    }
  }
}
