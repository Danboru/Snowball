package com.lenguyenthanh.snowball.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Video {

  public abstract int id();

  public abstract String name();

  public abstract String url();

  public abstract String thumbnail();

  public static Builder builder() {
    return new AutoValue_Video.Builder();
  }

  @AutoValue.Builder
  abstract static class Builder {
    abstract Builder id(int id);

    abstract Builder name(String name);

    abstract Builder url(String url);

    abstract Builder thumbnail(String thumbnail);

    abstract Video build();
  }
}
