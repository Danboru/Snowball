package com.lenguyenthanh.snowball.presentation.ui;

import com.lenguyenthanh.snowball.presentation.ui.network.NetworkBitmapClient;
import com.lenguyenthanh.snowball.presentation.ui.network.Tracker;

public interface UIDependencies {
  NetworkBitmapClient networkBitmapClient();

  Tracker tracker();
}
