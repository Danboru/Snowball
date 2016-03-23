package com.lenguyenthanh.snowball.presentation.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class VideoModel {

  public abstract int id();

  public abstract String name();

  public abstract String url();

  public abstract String thumbnail();

  public static Builder builder() {
    return new AutoValue_VideoModel.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder{
    public abstract Builder id(int id);

    public abstract Builder name(String name);

    public abstract Builder url(String url);

    public abstract Builder thumbnail(String thumbnail);

    public abstract VideoModel build();
  }
}