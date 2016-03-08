package com.lenguyenthanh.snowball.domain.common;

public final class Objects {

  private Objects() {
  }

  public static <T> T firstNonNull(T item1, T item2) {
    if (item1 != null) {
      return item1;
    }
    if (item2 != null) {
      return item2;
    }
    throw new NullPointerException("All items are null");
  }

  public static <T> T firstNonNull(T... items) {
    for (T item : items) {
      if (item != null) {
        return item;
      }
    }

    throw new NullPointerException("All items are null");
  }

  public static <T> boolean equals(T first, T second) {
    return first == null ? second == null : first.equals(second);
  }
}
