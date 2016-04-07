package com.lenguyenthanh.snowball.rules;

import android.support.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @see MockWebServerRule
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface NeedsMockWebServer {
    @NonNull String setupMethod() default "";
}
