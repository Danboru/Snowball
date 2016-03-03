/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lenguyenthanh.snowball.domain.getVideos;

import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

public class GetVideoList extends UseCase {

  private final VideoRepository videoRepository;

  @Inject
  public GetVideoList(VideoRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.videoRepository = userRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.videoRepository.videos();
  }
}
