package com.lenguyenthanh.snowball.app;

import android.app.Application;
import timber.log.Timber;

public interface AppDependencies {
  Application application();
  Timber.Tree timberTree();
}
