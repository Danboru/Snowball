package com.lenguyenthanh.snowball.presentation.exception;

import android.app.Application;
import com.lenguyenthanh.snowball.R;
import com.lenguyenthanh.snowball.data.exception.NetworkConnectionException;
import com.lenguyenthanh.snowball.util.di.scope.ActivityScope;
import java.net.UnknownHostException;
import javax.inject.Inject;

@ActivityScope
public class ErrorMessageFactory {
  // TODO: 3/16/16 https://github.com/android10/Android-CleanArchitecture/issues/71

  private final Application application;

  @Inject
  public ErrorMessageFactory(final Application application) {
    this.application = application;
  }

  public String create(Exception exception) {
    String message = application.getString(R.string.app_name);

    if (exception instanceof NetworkConnectionException || exception instanceof UnknownHostException) {
      message = application.getString(R.string.network_error_message);
    }

    return message;
  }
}
