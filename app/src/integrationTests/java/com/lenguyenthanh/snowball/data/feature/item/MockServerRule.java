package com.lenguyenthanh.snowball.data.feature.item;

import com.lenguyenthanh.snowball.app.AppModule;
import com.lenguyenthanh.snowball.app.IntegrationRobolectricTestRunner;
import com.lenguyenthanh.snowball.app.SnowBallApplication;
import com.lenguyenthanh.snowball.app.config.Configuration;
import com.lenguyenthanh.snowball.app.config.SupportModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.mockito.Mockito;

public class MockServerRule extends DaggerMockRule<SnowBallApplication.AppComponent> {

  private MockWebServer mockWebServer;

  public MockServerRule() {
    super(SnowBallApplication.AppComponent.class,
        new AppModule(IntegrationRobolectricTestRunner.snowballApp()), new SupportModule());

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
