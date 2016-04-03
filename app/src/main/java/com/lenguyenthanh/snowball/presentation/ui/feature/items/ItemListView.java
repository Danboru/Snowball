package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.snowball.presentation.model.ItemModel;
import java.util.List;

interface ItemListView extends NimbleView {

  void showLoading();

  void showRetry();

  void showError(String message);

  void hideRefresh();

  void renderItemList(List<ItemModel> userModelCollection);
}
