package com.lenguyenthanh.snowball.helper;

import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class StreamUtil {
  private StreamUtil() {
    // I know what I'm doing
  }

  public static String getResponseData(Class<?> clazz, final String filename)
      throws IOException {
    return IOUtils.toString(clazz.getResourceAsStream("/response/" + filename));
  }
}
