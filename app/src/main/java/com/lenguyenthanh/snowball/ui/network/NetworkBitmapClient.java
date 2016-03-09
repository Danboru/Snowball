package com.lenguyenthanh.snowball.ui.network;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public interface NetworkBitmapClient {
    void downloadInto(String url, ImageView imageView);

    /**
     * Picasso Implementation of NetworkBitmapClient
     */
    class PicassoBitmapClient implements NetworkBitmapClient {
        @Override public void downloadInto(String url, ImageView imageView) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}