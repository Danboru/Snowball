package com.lenguyenthanh.snowball.presentation.ui.feature.videos;

import com.lenguyenthanh.snowball.domain.DefaultSubscriber;
import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.util.common.Lists;
import com.lenguyenthanh.snowball.domain.exception.DefaultErrorBundle;
import com.lenguyenthanh.snowball.domain.exception.ErrorBundle;
import com.lenguyenthanh.snowball.domain.feature.video.Video;
import com.lenguyenthanh.snowball.presentation.exception.ErrorMessageFactory;
import com.lenguyenthanh.snowball.presentation.model.VideoModel;
import com.lenguyenthanh.snowball.presentation.model.VideoModelDataMapper;
import com.lenguyenthanh.snowball.presentation.ui.base.SaveStatePresenter;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

public class VideoListPresenterImpl extends SaveStatePresenter<VideoListView>
    implements VideoListPresenter {

  private final UseCase getVideoList;
  private final VideoModelDataMapper videoModelMapper;
  private final ErrorMessageFactory errorMessageFactory;
  private final NavigationCommand navigationCommand;

  private Collection<VideoModel> videoModels;

  @Inject
  public VideoListPresenterImpl(final UseCase getVideoList, final VideoModelDataMapper videoModelMapper,
      final ErrorMessageFactory errorMessageFactory, final NavigationCommand navigationCommand) {
    this.getVideoList = getVideoList;
    this.videoModelMapper = videoModelMapper;
    this.errorMessageFactory = errorMessageFactory;
    this.navigationCommand = navigationCommand;
  }

  @Override
  public void onDestroy() {
    getVideoList.unsubscribe();
  }

  @Override
  public void loadVideoList() {
    if (Lists.isEmptyOrNull(videoModels)) {
      getView().showLoading();
      getVideoList(new LoadVideoListSubscriber());
    } else {
      getView().renderVideoList(videoModels);
    }
  }

  @Override
  public void doRefresh() {
    getVideoList(new RefreshVideoListSubscriber());
  }

  @Override
  public void playVideo() {
    navigationCommand.navigate();
  }

  private void getVideoList(Subscriber<List<Video>> subscriber) {
    getVideoList.execute(subscriber);
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = errorMessageFactory.create(errorBundle.getException());
    getView().showError(errorMessage);
  }

  private void showVideoCollectionInView(Collection<Video> usersCollection) {
    videoModels = this.videoModelMapper.transform(usersCollection);
    getView().renderVideoList(videoModels);
  }

  private final class LoadVideoListSubscriber extends DefaultSubscriber<List<Video>> {

    @Override
    public void onError(Throwable e) {
      if(e instanceof Exception) {
        VideoListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      }
      getView().showRetry();
    }

    @Override
    public void onNext(List<Video> videos) {
      VideoListPresenterImpl.this.showVideoCollectionInView(videos);
    }
  }

  private final class RefreshVideoListSubscriber extends DefaultSubscriber<List<Video>> {

    @Override
    public void onCompleted() {
      getView().hideRefresh();
    }

    @Override
    public void onError(Throwable e) {
      if(e instanceof Exception) {
        VideoListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      }
      getView().hideRefresh();
    }

    @Override
    public void onNext(List<Video> videos) {
      VideoListPresenterImpl.this.showVideoCollectionInView(videos);
    }
  }
}
