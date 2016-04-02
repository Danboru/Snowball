package com.lenguyenthanh.snowball.presentation.model;

import com.lenguyenthanh.snowball.domain.feature.item.Item;
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

  ItemModelDataMapper dataMapper;

  @Before
  public void setUp() throws Exception {
    dataMapper = new ItemModelDataMapper();
  }

  @Test
  public void testTransform() throws Exception {
    Item item = createItem();
    ItemModel videoModel = dataMapper.transform(item);
    assertThat(videoModel.id()).isEqualTo(videoId);
    assertThat(videoModel.name()).isEqualTo(name);
    assertThat(videoModel.url()).isEqualTo(url);
    assertThat(videoModel.thumbnail()).isEqualTo(thumbnail);
  }

  @Test
  public void testTransformList() throws Exception {
    Item item1 = createItem();
    Item item2 = createItem();
    List<Item> list = Arrays.asList(item1, item2);
    Collection<ItemModel> videoCollection = dataMapper.transform(list);

    MatcherAssert.assertThat(videoCollection.toArray()[0], is(instanceOf(ItemModel.class)));
    MatcherAssert.assertThat(videoCollection.toArray()[1], is(instanceOf(ItemModel.class)));
    MatcherAssert.assertThat(videoCollection.size(), is(2));
  }

  private Item createItem() {
    return Item.builder().id(videoId).url(url).name(name).thumbnail(thumbnail).build();
  }
}