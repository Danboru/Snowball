package com.lenguyenthanh.snowball.presentation.model;

import com.lenguyenthanh.snowball.domain.feature.video.Video;
import com.lenguyenthanh.snowball.util.common.Lists;
import com.lenguyenthanh.snowball.util.di.scope.ActivityScope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

@ActivityScope
public class VideoModelDataMapper {

  @Inject
  public VideoModelDataMapper() {
    // For DI
  }

  public VideoModel transform(Video video) {
    if (video == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    return VideoModel.builder()
        .id(video.id())
        .name(video.name())
        .thumbnail(video.thumbnail())
        .url(video.url())
        .build();
  }

  public List<VideoModel> transform(List<Video> videoCollection) {
    if (Lists.isEmptyOrNull(videoCollection)) {
      return Collections.emptyList();
    }
    List<VideoModel> result = new ArrayList<>(videoCollection.size());
    for (Video video : videoCollection) {
      result.add(transform(video));
    }
    return result;
  }
}
