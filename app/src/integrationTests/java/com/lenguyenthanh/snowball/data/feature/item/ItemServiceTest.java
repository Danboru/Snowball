package com.lenguyenthanh.snowball.data.feature.item;

import com.lenguyenthanh.snowball.app.IntegrationRobolectricTestRunner;
import com.lenguyenthanh.snowball.data.entity.ItemEntity;
import com.lenguyenthanh.snowball.helper.StreamUtil;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.adapter.rxjava.HttpException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

@RunWith(IntegrationRobolectricTestRunner.class) public class ItemServiceTest {

  @Rule public MockServerRule mockServerRule = (MockServerRule) new MockServerRule().set(
      (appComponent) -> restApi = appComponent.videoService());

  private ItemService restApi;

  @Test public void testItems() throws Exception {
    mockServerRule.getMockWebServer()
        .enqueue(new MockResponse().setBody(StreamUtil.getResponseData(getClass(), "items.json")));

    // Get items from the API
    List<ItemEntity> entities = restApi.items().toBlocking().first();

    assertThat(entities).hasSize(3);

    assertThat(entities.get(0).videoId).isEqualTo(1);
    assertThat(entities.get(1).videoId).isEqualTo(2);
    assertThat(entities.get(2).videoId).isEqualTo(3);
  }

  @Test public void items_shouldThrowExceptionIfWebServerRespondError() {
    for (Integer errorCode : HttpCodes.clientAndServerSideErrorCodes()) {
      mockServerRule.getMockWebServer()
          .enqueue(new MockResponse().setStatus("HTTP/1.1 " + errorCode + " Not today"));
      try {
        restApi.items().toBlocking().first();
        fail("HttpException should be thrown for error code: " + errorCode);
      } catch (RuntimeException expected) {
        HttpException httpException = (HttpException) expected.getCause();
        assertThat(httpException.code()).isEqualTo(errorCode);
        assertThat(httpException.message()).isEqualTo("Not today");
      }
    }
  }
}