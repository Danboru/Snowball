package com.lenguyenthanh.snowball.ui;

import android.app.Application;
import android.support.annotation.NonNull;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.lenguyenthanh.snowball.ui.network.NetworkBitmapClient;
import com.lenguyenthanh.snowball.ui.network.PicassoBitmapClient;
import com.lenguyenthanh.snowball.ui.network.Tracker;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module
public class UIModule {

  @Provides
  @NonNull
  @Singleton
  public Picasso providePicasso(@NonNull Application application,
      @NonNull OkHttpClient okHttpClient) {
    return new Picasso.Builder(application).downloader(new OkHttp3Downloader(okHttpClient)).build();
  }

  @Provides
  @NonNull
  @Singleton
  public NetworkBitmapClient provideNetworkBitmapClient(@NonNull Picasso picasso) {
    return new PicassoBitmapClient(picasso);
  }

  @Provides
  @NonNull
  @Singleton
  public Tracker providerTracker() {
    return new Tracker.SimpleTracker();
  }
}
