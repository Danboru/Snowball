package com.lenguyenthanh.snowball.presentation.ui.feature.videos;

import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.presentation.exception.ErrorMessageFactory;
import com.lenguyenthanh.snowball.presentation.model.VideoModelDataMapper;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class VideoListPresenterImplTest {

  @Mock
  UseCase getVideoList;
  @Mock
  VideoModelDataMapper videoModelMapper;
  @Mock
  ErrorMessageFactory errorMessageFactory;
  @Mock
  NavigationCommand navigationCommand;
  @Mock
  VideoListView view;
  VideoListPresenterImpl presenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    presenter = new VideoListPresenterImpl(getVideoList, videoModelMapper, errorMessageFactory, navigationCommand);
    presenter.takeView(view);
  }

  @Test
  public void testOnDestroy() throws Exception {
    presenter.onDestroy();
    verify(getVideoList).unsubscribe();
  }

  @Test
  public void testLoadVideoList() throws Exception {
    presenter.loadVideoList();

  }

  @Test
  public void testDoRefresh() throws Exception {

  }

  @Test
  public void testPlayVideo() throws Exception {

  }
}