package com.lenguyenthanh.snowball.data.video;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.data.entity.VideoEntity;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface VideoService {

  @GET("videos") @NonNull
  Observable<List<VideoEntity>> videos();
}
