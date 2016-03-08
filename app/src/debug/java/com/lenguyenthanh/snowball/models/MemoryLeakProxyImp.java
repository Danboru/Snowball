package com.lenguyenthanh.snowball.models;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MemoryLeakProxyImp implements MemoryLeakProxy {

    @NonNull
    private final Application application;

    @Nullable
    private RefWatcher refWatcher;

    public MemoryLeakProxyImp(@NonNull Application qualityMattersApp) {
        this.application = qualityMattersApp;
    }

    @Override
    public void init() {
        refWatcher = LeakCanary.install(application);
    }

    @Override
    public void watch(@NonNull Object object) {
        if (refWatcher != null && object != null) {
            refWatcher.watch(object);
        }
    }
}
