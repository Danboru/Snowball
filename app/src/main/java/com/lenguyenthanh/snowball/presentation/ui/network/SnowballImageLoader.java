package com.lenguyenthanh.snowball.presentation.ui.network;

import android.support.annotation.NonNull;
import android.widget.ImageView;

public interface SnowballImageLoader {
  void downloadInto(@NonNull String url, @NonNull ImageView imageView);
}