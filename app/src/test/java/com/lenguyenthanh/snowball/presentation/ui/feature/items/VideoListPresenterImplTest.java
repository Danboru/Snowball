package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.presentation.exception.ErrorMessageFactory;
import com.lenguyenthanh.snowball.presentation.model.ItemModelDataMapper;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class VideoListPresenterImplTest {

  @Mock UseCase getItemList;
  @Mock ItemModelDataMapper itemModelDataMapper;
  @Mock ErrorMessageFactory errorMessageFactory;
  @Mock NavigationCommand navigationCommand;
  @Mock ItemListView view;
  ItemListPresenterImpl presenter;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    presenter = new ItemListPresenterImpl(getItemList, itemModelDataMapper, errorMessageFactory,
        navigationCommand);
    presenter.takeView(view);
  }

  @Test public void testOnDestroy() throws Exception {
    presenter.onDestroy();
    verify(getItemList).unsubscribe();
  }

  @Test public void testLoadItemList() throws Exception {
    presenter.loadVideoList();
  }

  @Test public void testDoRefresh() throws Exception {

  }

  @Test public void testPlayVideo() throws Exception {

  }
}