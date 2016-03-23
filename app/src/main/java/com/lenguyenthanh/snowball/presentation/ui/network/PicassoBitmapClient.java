package com.lenguyenthanh.snowball.presentation.ui.network;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class PicassoBitmapClient implements NetworkBitmapClient {

  private final Picasso picasso;

  @Inject
  public PicassoBitmapClient(final Picasso picasso) {
    this.picasso = picasso;
  }

  @Override
  public void downloadInto(String url, ImageView imageView) {
    picasso.load(url).fit().centerCrop().into(imageView);
  }
}
