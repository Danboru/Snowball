package com.lenguyenthanh.snowball.data.feature.item;

import com.lenguyenthanh.snowball.app.IntegrationRobolectricTestRunner;
import com.lenguyenthanh.snowball.data.entity.ItemEntity;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.adapter.rxjava.HttpException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

@RunWith(IntegrationRobolectricTestRunner.class)
public class ItemServiceTest {

  private final String response = "[\n"
      + "  {\n"
      + "    \"id\": \"1\",\n"
      + "    \"description\": \"If there's only one player who deserves that nickname, O Fenomeno, it's him. He's a phenomenon.\",\n"
      + "    \"title\": \"Ronaldo\",\n"
      + "    \"thumbnail\": \"https://image.freepik.com/free-photo/ronaldo---football-player-legends_26-638.jpg\"\n"
      + "  },\n"
      + "  {\n"
      + "    \"id\": \"2\",\n"
      + "    \"description\": \"Mark my words Gerrard is the greatest player to have played for #lfc sad sad day for everyone connected with the club.\",\n"
      + "    \"title\": \"Gerrard\",\n"
      + "    \"thumbnail\": \"https://scontent-hkg3-1.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/10671345_10202881746756109_7243997333740624908_n.jpg?oh=555f96c811e2ce69f5dee2ccfb8191fb&oe=57BD4908\"\n"
      + "  },\n"
      + "  {\n"
      + "    \"id\": \"3\",\n"
      + "    \"description\": \"I don’t think anyone ever looks forward to playing against Luis Suarez, he is a fantastic talent and a very dangerous player. He is a fantastic team mate, probably the best player I have played with.\",\n"
      + "    \"title\": \"Luis Suarez\",\n"
      + "    \"thumbnail\": \"http://i.dailymail.co.uk/i/pix/2014/06/27/article-2671421-0783B5E700000514-407_634x488.jpg\"\n"
      + "  }\n"
      + "]";

  @Rule
  public MockServerRule mockServerRule = (MockServerRule) new MockServerRule().set(
      (appComponent) -> restApi = appComponent.videoService());

  ItemService restApi;

  @Test
  public void testItems() throws Exception {
    mockServerRule.getMockWebServer().enqueue(new MockResponse().setBody(response));

    // Get items from the API
    List<ItemEntity> entities = restApi.items().toBlocking().first();

    assertThat(entities).hasSize(3);

    assertThat(entities.get(0).videoId).isEqualTo(1);
    assertThat(entities.get(1).videoId).isEqualTo(2);
    assertThat(entities.get(2).videoId).isEqualTo(3);
  }

  @Test
  public void items_shouldThrowExceptionIfWebServerRespondError() {
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