package com.lenguyenthanh.snowball.app;

import android.app.Application;
import com.lenguyenthanh.snowball.data.feature.video.VideoService;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;

public interface AppDependencies {
  Application application();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  VideoService videService();
}
