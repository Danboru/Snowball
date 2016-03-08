package com.lenguyenthanh.snowball.app.setting;

import android.support.annotation.NonNull;
import com.lenguyenthanh.snowball.data.network.OkHttpInterceptors;
import com.lenguyenthanh.snowball.data.network.OkHttpNetworkInterceptors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

@Singleton
public class OkHttpClientSetting{
  private final OkHttpClient okHttpClient;
  private final List<Interceptor> interceptors;
  private final List<Interceptor> networkInterceptors;

  @Inject
  public OkHttpClientSetting(final OkHttpClient okHttpClient,
      final @OkHttpInterceptors @NonNull List<Interceptor> interceptors,
      final @OkHttpNetworkInterceptors @NonNull List<Interceptor> networkInterceptors) {
    this.okHttpClient = okHttpClient;
    this.interceptors = interceptors;
    this.networkInterceptors = networkInterceptors;
  }

  public void initialize() {
    for (Interceptor interceptor : interceptors) {
      okHttpClient.interceptors().add(interceptor);
    }

    for (Interceptor networkInterceptor : networkInterceptors) {
      okHttpClient.networkInterceptors().add(networkInterceptor);
    }
  }
}