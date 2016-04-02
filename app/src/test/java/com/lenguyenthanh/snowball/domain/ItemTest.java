package com.lenguyenthanh.snowball.domain;

import com.lenguyenthanh.snowball.domain.feature.item.Item;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
  private static String FAKE_NAME = "item name";
  private static String FAKE_URL = "video_url";
  private static String FAKE_THUMBNAIL = "video_thumbnail";
  private static int FAKE_ID = 100;

  private Item item;

  @Test
  public void testNewVideo() throws Exception {
    item = Item.builder()
        .id(FAKE_ID)
        .name(FAKE_NAME)
        .thumbnail(FAKE_THUMBNAIL)
        .url(FAKE_URL)
        .build();
    assertThat(item.id(), is(FAKE_ID));
    assertThat(item.name(), is(FAKE_NAME));
    assertThat(item.thumbnail(), is(FAKE_THUMBNAIL));
    assertThat(item.url(), is(FAKE_URL));
  }
}
