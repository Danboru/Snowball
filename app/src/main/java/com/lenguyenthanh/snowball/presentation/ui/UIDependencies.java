package com.lenguyenthanh.snowball.presentation.ui;

import com.lenguyenthanh.snowball.presentation.ui.network.SnowballImageLoader;
import com.lenguyenthanh.snowball.presentation.ui.network.Tracker;

public interface UIDependencies {
  SnowballImageLoader networkBitmapClient();

  Tracker tracker();
}
