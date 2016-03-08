package com.lenguyenthanh.snowball.common;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.functions.Func1;
import rx.functions.Func2;

public final class Lists {

  private Lists() {
  }

  public static <T> boolean elementsEqual(List<T> list1, List<T> list2,
      Func2<T, T, Boolean> comparator) {
    if (list1 == null || list2 == null) {
      return false;
    }

    if (list1.size() != list2.size()) {
      return false;
    }

    for (int i = 0; i < list1.size(); i++) {
      if (!comparator.call(list1.get(i), list2.get(i))) {
        return false;
      }
    }

    return true;
  }

  public static <T> boolean isEmptyOrNull(List<T> list) {
    return list == null || list.isEmpty();
  }

  public static <T1, T2> List<T2> map(List<T1> list, Func1<T1, T2> converter) {
    ArrayList<T2> mappedList = new ArrayList<>(list.size());
    for (T1 item : list) {
      mappedList.add(converter.call(item));
    }

    return mappedList;
  }

  public static <T> List<T> asList(SparseArray<T> sparseArray) {
    if (sparseArray == null || sparseArray.size() == 0) return Collections.emptyList();
    int size = sparseArray.size();
    List<T> arrayList = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      arrayList.add(sparseArray.valueAt(i));
    return arrayList;
  }
}
