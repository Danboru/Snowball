package com.lenguyenthanh.snowball.presentation.model;

import com.lenguyenthanh.snowball.di.scope.ActivityScope;
import com.lenguyenthanh.snowball.domain.common.Lists;
import com.lenguyenthanh.snowball.domain.feature.video.Video;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

  public Collection<VideoModel> transform(Collection<Video> videoCollection) {
    if (Lists.isEmptyOrNull(videoCollection)) {
      return Collections.emptyList();
    }
    Collection<VideoModel> result = new ArrayList<>(videoCollection.size());
    for (Video video : videoCollection) {
      result.add(transform(video));
    }
    return result;
  }
}
