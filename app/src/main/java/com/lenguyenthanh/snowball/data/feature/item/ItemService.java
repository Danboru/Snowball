package com.lenguyenthanh.snowball.data.feature.item;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.data.entity.ItemEntity;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface ItemService {

  @GET("items") @NonNull Observable<List<ItemEntity>> items();
}
