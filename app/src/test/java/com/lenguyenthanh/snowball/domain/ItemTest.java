package com.lenguyenthanh.snowball.domain;

import com.lenguyenthanh.snowball.domain.feature.item.Item;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
  private static String FAKE_NAME = "item name";
  private static String FAKE_DESCRIPTION = "item_description";
  private static String FAKE_THUMBNAIL = "item_thumbnail";
  private static int FAKE_ID = 100;

  private Item item;

  @Test
  public void testNewVideo() throws Exception {
    item = Item.builder()
        .id(FAKE_ID)
        .name(FAKE_NAME)
        .thumbnail(FAKE_THUMBNAIL)
        .description(FAKE_DESCRIPTION)
        .build();
    assertThat(item.id(), is(FAKE_ID));
    assertThat(item.name(), is(FAKE_NAME));
    assertThat(item.thumbnail(), is(FAKE_THUMBNAIL));
    assertThat(item.description(), is(FAKE_DESCRIPTION));
  }
}
