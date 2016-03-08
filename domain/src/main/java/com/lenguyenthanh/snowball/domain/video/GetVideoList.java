package com.lenguyenthanh.snowball.domain.video;

import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

public class GetVideoList extends UseCase {

  private final VideoRepository videoRepository;

  @Inject
  public GetVideoList(VideoRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.videoRepository = userRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.videoRepository.videos();
  }
}
