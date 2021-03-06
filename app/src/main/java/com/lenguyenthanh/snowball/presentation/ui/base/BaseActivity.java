/*
 * Copyright 2016 Thanh Le.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lenguyenthanh.snowball.presentation.ui.base;

import butterknife.ButterKnife;
import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.nimble.view.NimbleActivity;
import com.lenguyenthanh.snowball.app.SnowBallApplication;

public abstract class BaseActivity<V extends NimbleView> extends NimbleActivity<V>
    implements NimbleView {

  @Override public void onContentChanged() {
    super.onContentChanged();
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    ButterKnife.unbind(this);
    super.onDestroy();
  }

  @Override protected void initialize() {
    super.initialize();
    setupActivityComponent();
  }

  protected void setupActivityComponent() {
    buildComponent(SnowBallApplication.get(this).getAppComponent());
  }

  abstract protected void buildComponent(SnowBallApplication.AppComponent appComponent);
}