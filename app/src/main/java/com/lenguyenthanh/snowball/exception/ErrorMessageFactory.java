package com.lenguyenthanh.snowball.exception;

import android.app.Application;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.data.exception.NetworkConnectionException;
import com.lenguyenthanh.snowball.di.scope.ActivityScope;
import javax.inject.Inject;

@ActivityScope
public class ErrorMessageFactory {

  private final Application application;

  @Inject
  public ErrorMessageFactory(final Application application) {
    this.application = application;
  }

  public String create(Exception exception) {
    String message = application.getString(R.string.app_name);

    if (exception instanceof NetworkConnectionException) {
      message = application.getString(R.string.app_name);
    }

    return message;
  }
}
