package com.lenguyenthanh.snowball.app;

import android.app.Application;
import com.lenguyenthanh.snowball.data.network.ServiceDependencies;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;

public interface AppDependencies extends ServiceDependencies {
  Application application();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();
}
