package com.lenguyenthanh.snowball.model;

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

public class VideoModelDataMapperTest {

  int videoId = 1;

  String url = "url";

  String name = "name";

  String thumbnail = "thumbnail";

  VideoModelDataMapper dataMapper;

  @Before
  public void setUp() throws Exception {
    dataMapper = new VideoModelDataMapper();
  }

  @Test
  public void testTransform() throws Exception {
    Video video = createVideo();
    VideoModel videoModel = dataMapper.transform(video);
    assertThat(videoModel.id()).isEqualTo(videoId);
    assertThat(videoModel.name()).isEqualTo(name);
    assertThat(videoModel.url()).isEqualTo(url);
    assertThat(videoModel.thumbnail()).isEqualTo(thumbnail);
  }

  @Test
  public void testTransformList() throws Exception {
    Video video1 = createVideo();
    Video video2 = createVideo();
    List<Video> list = Arrays.asList(video1, video2);
    Collection<VideoModel> videoCollection = dataMapper.transform(list);

    MatcherAssert.assertThat(videoCollection.toArray()[0], is(instanceOf(VideoModel.class)));
    MatcherAssert.assertThat(videoCollection.toArray()[1], is(instanceOf(VideoModel.class)));
    MatcherAssert.assertThat(videoCollection.size(), is(2));
  }

  private Video createVideo() {
    return Video.builder().id(videoId).url(url).name(name).thumbnail(thumbnail).build();
  }
}