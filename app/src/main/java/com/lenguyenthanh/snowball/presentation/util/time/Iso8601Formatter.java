package com.lenguyenthanh.snowball.presentation.util.time;

import com.lenguyenthanh.snowball.presentation.util.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Iso8601Formatter implements DateFormatter {
  public SimpleDateFormat create() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return simpleDateFormat;
  }
}
