package com.lenguyenthanh.snowball.app.config;

public interface Configuration {
  String getBaseApiUrl();

  class SimpleConfiguration implements Configuration{
    @Override
    public String getBaseApiUrl() {
      return "https://gist.githubusercontent.com/lenguyenthanh/5bb3bbd3404bb118bfc1/raw/d1b659449b3d17c3bf90bdf1d5f499da791bf376/";
    }
  }
}
