package com.lenguyenthanh.snowball.presentation.ui.feature.items;

import com.lenguyenthanh.snowball.domain.UseCase;
import com.lenguyenthanh.snowball.domain.exception.ErrorBundle;
import com.lenguyenthanh.snowball.domain.feature.item.Item;
import com.lenguyenthanh.snowball.presentation.exception.ErrorMessageFactory;
import com.lenguyenthanh.snowball.presentation.model.ItemModel;
import com.lenguyenthanh.snowball.presentation.model.ItemModelDataMapper;
import com.lenguyenthanh.snowball.presentation.util.ui.NavigationCommand;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemsPresenterImplTest {

    @Mock
    UseCase getItemList;
    @Mock
    ItemModelDataMapper itemModelDataMapper;
    @Mock
    ErrorMessageFactory errorMessageFactory;
    @Mock
    NavigationCommand navigationCommand;
    @Mock
    ItemListView view;
    @Mock
    List<ItemModel> models;
    @Mock
    List<Item> items;
    ItemListPresenterImpl presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        presenter = new ItemListPresenterImpl(getItemList, itemModelDataMapper, errorMessageFactory,
//                navigationCommand);
        presenter = Mockito.spy(new ItemListPresenterImpl(getItemList, itemModelDataMapper, errorMessageFactory,
                navigationCommand));

        presenter.itemModels = models;
        presenter.takeView(view);
    }

    @Test
    public void testOnDestroy() throws Exception {
        presenter.onDestroy();
        verify(getItemList).unsubscribe();
    }

    @Test
    public void loadItemList_shouldLoad_whenDataEmpty() throws Exception {
        when(models.isEmpty()).thenReturn(true);
        presenter.loadItemList();
        verify(view).showLoading();
        verify(getItemList).execute(any(Subscriber.class));
    }

    @Test
    public void loadItemList_shouldRender_whenDataNotEmpty() throws Exception {
        when(models.isEmpty()).thenReturn(false);
        presenter.loadItemList();
        verify(view).renderItemList(models);
    }

    @Test
    public void testDoRefresh() throws Exception {
        presenter.doRefresh();
        verify(getItemList).execute(any(Subscriber.class));
    }

    @Test
    public void testGotoDetail() throws Exception {
        presenter.gotoItemDetail();
        verify(navigationCommand).navigate();
    }

    @Test
    public void testShowListItemInView(){
        List<ItemModel> result = mock(List.class);
        when(itemModelDataMapper.transform(items)).thenReturn(result);

        presenter.showListItemInView(items);

        verify(itemModelDataMapper).transform(items);
        verify(view).renderItemList(result);
    }

    @Test
    public void testShowError_whenRefreshing(){
        presenter.showError(mock(Throwable.class), true);

        verify(presenter).showErrorMessage(any(ErrorBundle.class));
        verify(view).hideRefresh();
    }

    @Test
    public void testShowError_whenNotRefreshing(){
        presenter.showError(mock(Throwable.class), false);

        verify(presenter).showErrorMessage(any(ErrorBundle.class));
        verify(view).showRetry();
    }

    @Test
    public void testShowErrorMessage(){
        ErrorBundle errorBundle = mock(ErrorBundle.class);
        presenter.showErrorMessage(errorBundle);

        verify(errorBundle).getException();
        verify(view).showError(any(String.class));
    }

}