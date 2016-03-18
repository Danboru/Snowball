package com.lenguyenthanh.snowball.app.config;

public interface Configuration {
  String getBaseApiUrl();

  class SimpleConfiguration implements Configuration{
    @Override
    public String getBaseApiUrl() {
      return "https://gist.githubusercontent.com/lenguyenthanh/5bb3bbd3404bb118bfc1/raw/91809efb7abbc43870465cb594cdfd0bce67bb26/";
    }
  }
}
