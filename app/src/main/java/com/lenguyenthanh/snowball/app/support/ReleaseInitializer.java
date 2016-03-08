package com.lenguyenthanh.snowball.app.support;

import javax.inject.Inject;
import timber.log.Timber;

public class ReleaseInitializer implements Initializer {

  private final Timber.Tree logTree;

  @Inject
  public ReleaseInitializer(final Timber.Tree logTree) {
    this.logTree = logTree;
  }

  @Override
  public void initialize() {
    initializeLog();
  }

  private void initializeLog() {
    Timber.plant(logTree);
  }
}
