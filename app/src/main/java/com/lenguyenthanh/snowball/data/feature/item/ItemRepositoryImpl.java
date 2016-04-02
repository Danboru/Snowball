package com.lenguyenthanh.snowball.data.feature.item;

import com.lenguyenthanh.snowball.data.entity.mapper.ItemEntityDataMapper;
import com.lenguyenthanh.snowball.domain.feature.item.Item;
import com.lenguyenthanh.snowball.domain.feature.item.ItemRepository;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class ItemRepositoryImpl implements ItemRepository {
  private final ItemService itemService;
  private final ItemEntityDataMapper itemEntityDataMapper;

  @Inject public ItemRepositoryImpl(final ItemService itemService,
      final ItemEntityDataMapper itemEntityDataMapper) {
    this.itemService = itemService;
    this.itemEntityDataMapper = itemEntityDataMapper;
  }

  @Override public Observable<List<Item>> videos() {
    return itemService.items().map(itemEntityDataMapper::transform);
  }
}
