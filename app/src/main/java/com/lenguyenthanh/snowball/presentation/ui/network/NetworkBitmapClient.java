package com.lenguyenthanh.snowball.presentation.ui.network;

import android.widget.ImageView;

public interface NetworkBitmapClient {
    void downloadInto(String url, ImageView imageView);
}