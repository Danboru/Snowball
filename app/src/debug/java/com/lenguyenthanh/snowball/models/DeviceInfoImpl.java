package com.lenguyenthanh.snowball.models;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.lenguyenthanh.snowball.util.common.Strings;

public class DeviceInfoImpl implements DeviceInfo {

  private Context context;

  public DeviceInfoImpl(Context context) {
    this.context = context;
  }

  @Override
  public String getDeviceManufacturer() {
    return Strings.truncateAt(Build.MANUFACTURER, 20);
  }

  @Override
  public String getDeviceModel() {
    return Strings.truncateAt(Build.MODEL, 20);
  }

  @Override
  public String getDeviceApi() {
    return String.valueOf(Build.VERSION.SDK_INT);
  }

  @Override
  public String getDeviceRelease() {
    return Build.VERSION.RELEASE;
  }

  @Override
  public String getDeviceResolution() {
    DisplayMetrics displayMetrics = getDisplayMetrics();
    return displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
  }

  @Override
  public String getDeviceDensity() {
    return getDensityString(getDisplayMetrics());
  }

  private DisplayMetrics getDisplayMetrics() {
    return context.getResources().getDisplayMetrics();
  }

  private String getDensityString(DisplayMetrics displayMetrics) {
    switch (displayMetrics.densityDpi) {
      case DisplayMetrics.DENSITY_LOW:
        return "ldpi";
      case DisplayMetrics.DENSITY_MEDIUM:
        return "mdpi";
      case DisplayMetrics.DENSITY_HIGH:
        return "hdpi";
      case DisplayMetrics.DENSITY_XHIGH:
        return "xhdpi";
      case DisplayMetrics.DENSITY_XXHIGH:
        return "xxhdpi";
      case DisplayMetrics.DENSITY_XXXHIGH:
        return "xxxhdpi";
      case DisplayMetrics.DENSITY_TV:
        return "tvdpi";
      default:
        return String.valueOf(displayMetrics.densityDpi);
    }
  }
}
