package com.lenguyenthanh.snowball.data.entity.mapper;

import com.lenguyenthanh.snowball.data.entity.VideoEntity;
import com.lenguyenthanh.snowball.domain.common.Lists;
import com.lenguyenthanh.snowball.domain.feature.video.Video;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

import static java.util.Collections.emptyList;

public class VideoEntityDataMapper {

  @Inject
  public VideoEntityDataMapper() {
    // use for Injection
  }

  public Video transform(VideoEntity videoEntity) {
    if (videoEntity != null) {
      return Video.builder()
          .id(videoEntity.videoId)
          .name(videoEntity.name)
          .thumbnail(videoEntity.thumbnail)
          .url(videoEntity.url)
          .build();
    }
    return null;
  }

  public List<Video> transform(Collection<VideoEntity> videoEntityCollection) {
    if (Lists.isEmptyOrNull(videoEntityCollection)) {
      return emptyList();
    }
    List<Video> userList = new ArrayList<>(videoEntityCollection.size());
    for (VideoEntity userEntity : videoEntityCollection) {
      Video video = transform(userEntity);
      if (video != null) {
        userList.add(video);
      }
    }
    return userList;
  }
}
