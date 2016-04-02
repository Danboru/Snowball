package com.lenguyenthanh.snowball.presentation.model;

import com.lenguyenthanh.snowball.domain.feature.item.Item;
import com.lenguyenthanh.snowball.util.common.Lists;
import com.lenguyenthanh.snowball.util.di.scope.ActivityScope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

@ActivityScope public class ItemModelDataMapper {

  @Inject public ItemModelDataMapper() {
    // For DI
  }

  public ItemModel transform(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    return ItemModel.builder()
        .id(item.id())
        .name(item.name())
        .thumbnail(item.thumbnail())
        .url(item.url())
        .build();
  }

  public List<ItemModel> transform(List<Item> itemCollection) {
    if (Lists.isEmptyOrNull(itemCollection)) {
      return Collections.emptyList();
    }
    List<ItemModel> result = new ArrayList<>(itemCollection.size());
    for (Item item : itemCollection) {
      result.add(transform(item));
    }
    return result;
  }
}
