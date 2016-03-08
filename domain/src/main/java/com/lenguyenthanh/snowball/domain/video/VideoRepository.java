package com.lenguyenthanh.snowball.domain.video;

import com.lenguyenthanh.snowball.domain.Video;
import java.util.List;
import rx.Observable;

public interface VideoRepository {
  Observable<List<Video>> videos();
}
