package com.lenguyenthanh.snowball.data.mapper;

import com.lenguyenthanh.snowball.data.entity.ItemEntity;
import com.lenguyenthanh.snowball.data.entity.mapper.ItemEntityDataMapper;
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

public class ItemEntityDataMapperTest {

  private final int id = 1;

  private final String description = "url";

  private final String name = "name";

  private final String thumbnail = "thumbnail";

  private ItemEntityDataMapper dataMapper;

  @Before
  public void setUp() throws Exception {
    dataMapper = new ItemEntityDataMapper();
  }

  @Test
  public void testTransform() throws Exception {
    ItemEntity itemEntity = createItemEntity();
    Item video = dataMapper.transform(itemEntity);
    assertThat(video.id()).isEqualTo(id);
    assertThat(video.name()).isEqualTo(name);
    assertThat(video.description()).isEqualTo(description);
    assertThat(video.thumbnail()).isEqualTo(thumbnail);
  }

  @Test
  public void testListTransform() throws Exception {
    ItemEntity videoEntity1 = createItemEntity();
    ItemEntity videoEntity2 = createItemEntity();
    List<ItemEntity> list = Arrays.asList(videoEntity1, videoEntity2);
    Collection<Item> videoCollection = dataMapper.transform(list);

    MatcherAssert.assertThat(videoCollection.toArray()[0], is(instanceOf(Item.class)));
    MatcherAssert.assertThat(videoCollection.toArray()[1], is(instanceOf(Item.class)));
    MatcherAssert.assertThat(videoCollection.size(), is(2));
  }

  ItemEntity createItemEntity(){
    ItemEntity itemEntity = new ItemEntity();
    itemEntity.description = description;
    itemEntity.videoId = id;
    itemEntity.name = name;
    itemEntity.thumbnail = thumbnail;
    return itemEntity;
  }
}