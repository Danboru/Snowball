package com.lenguyenthanh.snowball;

import android.app.Application;
import android.support.annotation.NonNull;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lenguyenthanh.snowball.app.Initializer;
import com.lenguyenthanh.snowball.app.support.ActivityHierarchyServer;
import com.lenguyenthanh.snowball.models.MemoryLeakProxy;
import com.lenguyenthanh.snowball.models.MemoryLeakProxyImp;
import dagger.Module;
import dagger.Provides;
import hu.supercluster.paperwork.Paperwork;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import timber.log.Timber;

@Module
@Singleton
public class DebugModule {
    @Provides
    @Singleton
    MemoryLeakProxy provideLeakCanary(Application application){
        return new MemoryLeakProxyImp(application);
    }

    @Provides
    @Singleton
    ActivityHierarchyServer provideActivityHierarchyServer(DebugActivityHierarchyServer server) {
        return server;
    }

    @Provides
    @Singleton
    Timber.Tree provideTimber() {
        return new Timber.DebugTree();
    }

    @Provides
    @Singleton
    Interceptor provideDebugInterceptor(){
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    Initializer provideInitializer(DebugInitializer debugInitializer){
        return debugInitializer;
    }

    @Provides
    @NonNull
    @Singleton
    public Paperwork providePaperwork(@NonNull Application application) {
        return new Paperwork(application);
    }
}
