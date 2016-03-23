package com.lenguyenthanh.snowball.domain;

import com.lenguyenthanh.snowball.domain.feature.video.Video;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VideoTest {
  private static String FAKE_NAME = "video name";
  private static String FAKE_URL = "video_url";
  private static String FAKE_THUMBNAIL = "video_thumbnail";
  private static int FAKE_ID = 100;

  private Video video;

  @Test
  public void testNewVideo() throws Exception {
    video = Video.builder()
        .id(FAKE_ID)
        .name(FAKE_NAME)
        .thumbnail(FAKE_THUMBNAIL)
        .url(FAKE_URL)
        .build();
    assertThat(video.id(), is(FAKE_ID));
    assertThat(video.name(), is(FAKE_NAME));
    assertThat(video.thumbnail(), is(FAKE_THUMBNAIL));
    assertThat(video.url(), is(FAKE_URL));
  }
}
