package com.lenguyenthanh.snowball.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoEntity {
  @JsonProperty("id")
  public int videoId;

  @JsonProperty("url")
  public String url;

  @JsonProperty("title")
  public String name;

  @JsonProperty("thumbnail")
  public String thumbnail;
}
