package com.lenguyenthanh.snowball.data.mapper;

import com.lenguyenthanh.snowball.data.entity.VideoEntity;
import com.lenguyenthanh.snowball.data.entity.mapper.VideoEntityDataMapper;
import com.lenguyenthanh.snowball.domain.Video;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class VideoEntityDataMapperTest {

  int videoId = 1;

  String url = "url";

  String name = "name";

  String thumbnail = "thumbnail";

  VideoEntityDataMapper dataMapper;

  @Before
  public void setUp() throws Exception {
    dataMapper = new VideoEntityDataMapper();
  }

  @Test
  public void testTransform() throws Exception {
    VideoEntity videoEntity = createVideoEntity();
    Video video = dataMapper.transform(videoEntity);
    assertThat(video.id()).isEqualTo(videoId);
    assertThat(video.name()).isEqualTo(name);
    assertThat(video.url()).isEqualTo(url);
    assertThat(video.thumbnail()).isEqualTo(thumbnail);
  }

  @Test
  public void testListTransform() throws Exception {
    VideoEntity videoEntity1 = createVideoEntity();
    VideoEntity videoEntity2 = createVideoEntity();
    List<VideoEntity> list = Arrays.asList(videoEntity1, videoEntity2);
    Collection<Video> videoCollection = dataMapper.transform(list);

    MatcherAssert.assertThat(videoCollection.toArray()[0], is(instanceOf(Video.class)));
    MatcherAssert.assertThat(videoCollection.toArray()[1], is(instanceOf(Video.class)));
    MatcherAssert.assertThat(videoCollection.size(), is(2));
  }

  VideoEntity createVideoEntity(){
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.url = url;
    videoEntity.videoId = videoId;
    videoEntity.name = name;
    videoEntity.thumbnail = thumbnail;
    return videoEntity;
  }
}