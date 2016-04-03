package com.lenguyenthanh.snowball.domain.feature.item;

import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.executor.PostExecutionThread;
import com.lenguyenthanh.snowball.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

public class GetItemList extends UseCase {

  private final ItemRepository itemRepository;

  @Inject public GetItemList(ItemRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.itemRepository = userRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.itemRepository.items();
  }
}
