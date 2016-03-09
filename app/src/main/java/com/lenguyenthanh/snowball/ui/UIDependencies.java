package com.lenguyenthanh.snowball.ui;

import com.lenguyenthanh.snowball.ui.network.NetworkBitmapClient;
import com.lenguyenthanh.snowball.ui.network.Tracker;

public interface UIDependencies {
  NetworkBitmapClient networkBitmapClient();

  Tracker tracker();
}
