package com.lenguyenthanh.snowball.domain.feature.item;

import java.util.List;
import rx.Observable;

public interface ItemRepository {
  Observable<List<Item>> items();
}
