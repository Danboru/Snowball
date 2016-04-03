package com.lenguyenthanh.snowball.domain.feature.item;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class Item {

  public abstract int id();

  public abstract String name();

  public abstract String description();

  public abstract String thumbnail();

  public static Builder builder() {
    return new AutoValue_Item.Builder();
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder id(int id);

    public abstract Builder name(String name);

    public abstract Builder description(String description);

    public abstract Builder thumbnail(String thumbnail);

    public abstract Item build();
  }
}