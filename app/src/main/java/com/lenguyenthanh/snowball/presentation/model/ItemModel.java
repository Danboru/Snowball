package com.lenguyenthanh.snowball.presentation.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class ItemModel {

  public abstract int id();

  public abstract String name();

  public abstract String description();

  public abstract String thumbnail();

  public static Builder builder() {
    return new AutoValue_ItemModel.Builder();
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder id(int id);

    public abstract Builder name(String name);

    public abstract Builder description(String url);

    public abstract Builder thumbnail(String thumbnail);

    public abstract ItemModel build();
  }
}