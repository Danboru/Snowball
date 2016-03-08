package com.lenguyenthanh.snowball.app;

import android.app.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import timber.log.Timber;

public interface AppDependencies {
  Application application();
  Timber.Tree timberTree();
  ObjectMapper objectMapper();
}
