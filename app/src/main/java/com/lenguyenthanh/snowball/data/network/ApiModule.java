package com.lenguyenthanh.snowball.data.network;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenguyenthanh.snowball.BuildConfig;
import com.lenguyenthanh.snowball.app.config.Configuration;
import com.lenguyenthanh.snowball.data.feature.item.ItemService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module @Singleton public final class ApiModule {

  @Provides @Singleton @NonNull public Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient,
      @NonNull Configuration configuration, @NonNull Converter.Factory factory) {
    return new Retrofit.Builder().baseUrl(configuration.getBaseApiUrl())
        .client(okHttpClient)
        .addConverterFactory(factory)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .validateEagerly(BuildConfig.DEBUG)
        .build();
  }

  @Provides @Singleton @NonNull Converter.Factory provideConverter(
      @NonNull ObjectMapper objectMapper) {
    return JacksonConverterFactory.create(objectMapper);
  }

  @Provides @NonNull ItemService provideVideoService(Retrofit retrofit) {
    return retrofit.create(ItemService.class);
  }
}