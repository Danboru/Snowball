package com.lenguyenthanh.snowball.data.entity.mapper;

import com.lenguyenthanh.snowball.data.entity.ItemEntity;
import com.lenguyenthanh.snowball.domain.feature.item.Item;
import com.lenguyenthanh.snowball.util.common.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

import static java.util.Collections.emptyList;

public class ItemEntityDataMapper {

  @Inject public ItemEntityDataMapper() {
    // I know what I d ;)
  }

  public Item transform(ItemEntity itemEntity) {
    if (itemEntity != null) {
      return Item.builder()
          .id(itemEntity.videoId)
          .name(itemEntity.name)
          .thumbnail(itemEntity.thumbnail)
          .description(itemEntity.description)
          .build();
    }
    return null;
  }

  public List<Item> transform(Collection<ItemEntity> itemEntityCollection) {
    if (Lists.isEmptyOrNull(itemEntityCollection)) {
      return emptyList();
    }
    List<Item> userList = new ArrayList<>(itemEntityCollection.size());
    for (ItemEntity userEntity : itemEntityCollection) {
      Item item = transform(userEntity);
      if (item != null) {
        userList.add(item);
      }
    }
    return userList;
  }
}
