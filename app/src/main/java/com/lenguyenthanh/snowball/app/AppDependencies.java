package com.lenguyenthanh.snowball.app;

import android.app.Application;
import com.lenguyenthanh.snowball.app.config.Configuration;
import com.lenguyenthanh.snowball.data.network.ServiceDependencies;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;
import com.lenguyenthanh.snowball.presentation.ui.UIDependencies;
import com.lenguyenthanh.snowball.util.di.qualifier.ApiKey;

public interface AppDependencies extends ServiceDependencies, UIDependencies {
  Application application();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  Configuration configuration();

  @ApiKey
  String apiKey();
}
