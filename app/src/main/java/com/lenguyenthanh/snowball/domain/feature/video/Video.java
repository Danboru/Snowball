package com.lenguyenthanh.snowball.domain.feature.video;

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
  public abstract static class Builder{
    public abstract Builder id(int id);

    public abstract Builder name(String name);

    public abstract Builder url(String url);

    public abstract Builder thumbnail(String thumbnail);

    public abstract Video build();
  }
}