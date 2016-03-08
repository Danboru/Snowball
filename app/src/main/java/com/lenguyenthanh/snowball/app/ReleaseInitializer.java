package com.lenguyenthanh.snowball.app;

import com.lenguyenthanh.snowball.app.setting.OkHttpClientSetting;
import javax.inject.Inject;
import timber.log.Timber;

public class ReleaseInitializer implements Initializer {

  private final Timber.Tree logTree;
  private final OkHttpClientSetting okHttpClientSetting;

  @Inject
  public ReleaseInitializer(final Timber.Tree logTree, final OkHttpClientSetting okHttpClientSetting) {
    this.logTree = logTree;
    this.okHttpClientSetting = okHttpClientSetting;
  }

  @Override
  public void initialize() {
    initializeLog();
    initializeOkHttpClientLogger();
  }

  private void initializeOkHttpClientLogger() {
    okHttpClientSetting.initialize();
  }

  private void initializeLog() {
    Timber.plant(logTree);
  }
}
