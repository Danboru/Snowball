package com.lenguyenthanh.snowball.data.feature.video;

import com.lenguyenthanh.snowball.data.entity.mapper.VideoEntityDataMapper;
import com.lenguyenthanh.snowball.domain.Video;
import com.lenguyenthanh.snowball.domain.video.VideoRepository;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class VideoRepositoryImpl implements VideoRepository {
  private final VideoService videoService;
  private final VideoEntityDataMapper videoEntityDataMapper;

  @Inject
  public VideoRepositoryImpl(final VideoService videoService,
      final VideoEntityDataMapper videoEntityDataMapper) {
    this.videoService = videoService;
    this.videoEntityDataMapper = videoEntityDataMapper;
  }

  @Override
  public Observable<List<Video>> videos() {
    return videoService.videos().map(videoEntityDataMapper::transform);
  }
}
