package com.lenguyenthanh.snowball.ui.feature.videos;

import com.lenguyenthanh.snowball.domain.DefaultSubscriber;
import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.Video;
import com.lenguyenthanh.snowball.domain.exception.DefaultErrorBundle;
import com.lenguyenthanh.snowball.domain.exception.ErrorBundle;
import com.lenguyenthanh.snowball.exception.ErrorMessageFactory;
import com.lenguyenthanh.snowball.model.VideoModel;
import com.lenguyenthanh.snowball.model.VideoModelMapper;
import com.lenguyenthanh.snowball.ui.base.SaveStatePresenter;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

public class VideoListPresenterImpl extends SaveStatePresenter<VideoListView>
    implements VideoListPresenter {

  private final UseCase getVideoList;
  private final VideoModelMapper videoModelMapper;
  private final ErrorMessageFactory errorMessageFactory;

  @Inject
  public VideoListPresenterImpl(final UseCase getVideoList, final VideoModelMapper videoModelMapper,
      final ErrorMessageFactory errorMessageFactory) {
    this.getVideoList = getVideoList;
    this.videoModelMapper = videoModelMapper;
    this.errorMessageFactory = errorMessageFactory;
  }

  @Override
  public void onDestroy() {
    getVideoList.unsubscribe();
  }

  @Override
  public void loadVideoList() {
    getView().showLoading();
    getVideoList(new LoadVideoListSubscriber());
  }

  @Override
  public void doRefresh() {
    getVideoList(new RefreshVideoListSubscriber());
  }

  private void getVideoList(Subscriber<List<Video>> subscriber) {
    getVideoList.execute(subscriber);
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = errorMessageFactory.create(errorBundle.getException());
    getView().showError(errorMessage);
  }

  private void showVideoCollectionInView(Collection<Video> usersCollection) {
    final Collection<VideoModel> videoModelCollection =
        this.videoModelMapper.transform(usersCollection);
    getView().renderVideoList(videoModelCollection);
  }

  private final class LoadVideoListSubscriber extends DefaultSubscriber<List<Video>> {

    @Override
    public void onError(Throwable e) {
      VideoListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
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
      VideoListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      getView().hideRefresh();
    }

    @Override
    public void onNext(List<Video> videos) {
      VideoListPresenterImpl.this.showVideoCollectionInView(videos);
    }
  }
}
