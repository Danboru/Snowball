package com.lenguyenthanh.snowball.data.feature.video;

import com.lenguyenthanh.snowball.app.IntegrationRobolectricTestRunner;
import com.lenguyenthanh.snowball.data.entity.VideoEntity;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.adapter.rxjava.HttpException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

@RunWith(IntegrationRobolectricTestRunner.class)
public class VideoServiceTest {

  private final String response = "[\n" + "  {\n" + "    \"id\": \"1\",\n"
      + "    \"url\": \"http://media.snowballnow.com/video/upload/v1451206914/lcgbutfkqbkhvrfdurrp.mp4\",\n"
      + "    \"title\": \"Motobike\",\n"
      + "    \"thumbnail\": \"https://www.evernote.com/shard/s159/sh/d2345890-5698-48bb-91e8-bcf763e86d2b/d1b288341ff44254/res/a7c7b1d0-8a81-4c64-b5cc-5e3dad73bf45/skitch.jpg?resizeSmall&width=832\"\n"
      + "  },\n" + "  {\n" + "    \"id\": \"2\",\n"
      + "    \"url\": \"http://media.snowballnow.com/video/upload/v1450752299/deff3bxsmpsrow4i40e0.mp4\",\n"
      + "    \"title\": \"Beryl\",\n"
      + "    \"thumbnail\": \"https://www.evernote.com/shard/s159/sh/870e4057-fc67-432f-8616-8b24e3e1c5c9/8c4144b2f727dc7c/res/a4dd159c-6a26-4404-aad3-724a891fe432/skitch.jpg?resizeSmall&width=832\"\n"
      + "  },\n" + "  {\n" + "    \"id\": \"3\",\n"
      + "    \"url\": \"http://media.snowballnow.com/video/upload/v1450787135/poareeehaxfqhcyavnnl.mp4\",\n"
      + "    \"title\": \"Ideal\",\n"
      + "    \"thumbnail\": \"https://www.evernote.com/shard/s159/sh/b2794124-2578-41c3-9be6-82500dca1bbe/e65286fe8884e73d/res/0db48c73-216b-40b3-9eb3-596271c276a7/skitch.jpg?resizeSmall&width=832\"\n"
      + "  }\n" + "]";

  @Rule
  public MockServerRule mockServerRule = (MockServerRule)new MockServerRule().set((appComponent) -> restApi = appComponent.videoService());

  VideoService restApi;

  @Test
  public void testVideos() throws Exception {
    mockServerRule.getMockWebServer().enqueue(new MockResponse().setBody(response));

    // Get items from the API
    List<VideoEntity> videoEntities = restApi.videos().toBlocking().first();

    assertThat(videoEntities).hasSize(3);

    assertThat(videoEntities.get(0).videoId).isEqualTo(1);
    assertThat(videoEntities.get(1).videoId).isEqualTo(2);
    assertThat(videoEntities.get(2).videoId).isEqualTo(3);
  }

  @Test
  public void items_shouldThrowExceptionIfWebServerRespondError() {
    for (Integer errorCode : HttpCodes.clientAndServerSideErrorCodes()) {
      mockServerRule.getMockWebServer().enqueue(
          new MockResponse().setStatus("HTTP/1.1 " + errorCode + " Not today"));
      try {
        List<VideoEntity> videoEntities = restApi.videos().toBlocking().first();
        fail("HttpException should be thrown for error code: " + errorCode);
      } catch (RuntimeException expected) {
        HttpException httpException = (HttpException) expected.getCause();
        assertThat(httpException.code()).isEqualTo(errorCode);
        assertThat(httpException.message()).isEqualTo("Not today");
      }
    }
  }
}