package com.lenguyenthanh.snowball.rules;

import com.lenguyenthanh.snowball.TestUtils;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.app.config.Configuration;
import com.lenguyenthanh.snowball.app.config.SupportModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.mockito.Mockito;

/**
 * JUnit test rule for mocking web server!
 */
public class MockWebServerRule extends DaggerMockRule<SnowBallApplication.AppComponent> {

  private MockWebServer mockWebServer;

  public MockWebServerRule() {
    super(SnowBallApplication.AppComponent.class, TestUtils.app(), new SupportModule());

    mockWebServer = new MockWebServer();
    try {
      mockWebServer.start();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Configuration configuration = Mockito.mock(Configuration.class);
    Mockito.when(configuration.getBaseApiUrl()).thenReturn(mockWebServer.url("").toString());

    provides(Configuration.class, configuration);
  }

  public MockWebServer getMockWebServer() {
    return mockWebServer;
  }
}