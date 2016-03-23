package com.lenguyenthanh.snowball.data.feature.video.entities;

import com.lenguyenthanh.snowball.app.IntegrationRobolectricTestRunner;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(IntegrationRobolectricTestRunner.class)
public class VideoTest {

  // Why test JSON serialization/deserialization?
  // 1. Update JSON libraries without worrying about breaking changes.
  // 2. Be sure that @JsonIgnore and similar annotations do not affect expected behavior (cc @karlicos).
  @Test
  public void fromJson() throws IOException {

  }
}