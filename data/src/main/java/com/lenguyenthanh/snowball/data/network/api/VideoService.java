package com.lenguyenthanh.snowball.data.network.api;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.data.entity.VideoEntity;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface VideoService {

  @GET("items") @NonNull
  Observable<List<VideoEntity>> videos();
}
