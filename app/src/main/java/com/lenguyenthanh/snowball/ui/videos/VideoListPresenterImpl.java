package com.lenguyenthanh.snowball.ui.videos;

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
    getView().hideLoading();
    getView().showLoading();
    getVideoList.execute(new VideoListSubscriber());
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

  private final class VideoListSubscriber extends DefaultSubscriber<List<Video>> {

    @Override
    public void onCompleted() {
      getView().hideLoading();
    }

    @Override
    public void onError(Throwable e) {
      getView().hideLoading();
      VideoListPresenterImpl.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      getView().showRetry();
    }

    @Override
    public void onNext(List<Video> videos) {
      VideoListPresenterImpl.this.showVideoCollectionInView(videos);
    }
  }
}
