package com.lenguyenthanh.snowball.app.config;

public interface Configuration {
  String getBaseApiUrl();

  class SimpleConfiguration implements Configuration {
    @Override public String getBaseApiUrl() {
      return "https://gist.githubusercontent.com/lenguyenthanh/c1978681327d22227689b8819c2327f4/raw/bf7bb64d194f4f55bbff5a8dca4dd81126e1c278/";
    }
  }
}
