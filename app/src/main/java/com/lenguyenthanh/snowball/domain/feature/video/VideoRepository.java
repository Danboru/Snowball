package com.lenguyenthanh.snowball.domain.feature.video;

import java.util.List;
import rx.Observable;

public interface VideoRepository {
  Observable<List<Video>> videos();
}
