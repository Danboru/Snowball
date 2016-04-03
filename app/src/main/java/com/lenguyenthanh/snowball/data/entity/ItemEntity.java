package com.lenguyenthanh.snowball.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemEntity {

  @JsonProperty("id") public int videoId;

  @JsonProperty("description") public String description;

  @JsonProperty("title") public String name;

  @JsonProperty("thumbnail") public String thumbnail;
}
