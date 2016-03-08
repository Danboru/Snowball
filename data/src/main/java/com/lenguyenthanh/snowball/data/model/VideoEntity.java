package com.lenguyenthanh.snowball.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoEntity {
  @JsonProperty("id")
  private int videoId;

  @JsonProperty("url")
  private String url;

  @JsonProperty("name")
  private String name;
}
