package com.lenguyenthanh.snowball.data.network;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenguyenthanh.snowball.data.BuildConfig;
import com.lenguyenthanh.snowball.data.feature.video.VideoService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
@Singleton
public final class ApiModule {

  @NonNull
  private final ChangeableBaseUrl changeableBaseUrl;

  public ApiModule(@NonNull String baseUrl) {
    changeableBaseUrl = new ChangeableBaseUrl(baseUrl);
  }

  @Provides
  @Singleton
  @NonNull
  public ChangeableBaseUrl provideChangeableBaseUrl() {
    return changeableBaseUrl;
  }

  @Provides
  @Singleton
  @NonNull
  public Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient,
      @NonNull ChangeableBaseUrl changeableBaseUrl, @NonNull Converter.Factory factory) {
    return new Retrofit.Builder()
        .baseUrl(changeableBaseUrl)
        .client(okHttpClient)
        .addConverterFactory(factory)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .validateEagerly(BuildConfig.DEBUG)
        .build();
  }

  @Provides
  @Singleton
  @NonNull
  Converter.Factory provideConverter(@NonNull ObjectMapper objectMapper) {
    return JacksonConverterFactory.create(objectMapper);
  }

  @Provides
  @NonNull
  VideoService provideVideoService(Retrofit retrofit){
    return retrofit.create(VideoService.class);
  }
}