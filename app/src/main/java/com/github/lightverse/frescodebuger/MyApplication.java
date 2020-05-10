package com.github.lightverse.frescodebuger;

import android.app.Application;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.github.lightverse.fresco.learner.FrescoDumperPlugin;

import javax.annotation.Nullable;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(new Stetho.Initializer(this) {
            @Nullable
            @Override
            protected Iterable<DumperPlugin> getDumperPlugins() {
                return new Stetho.DefaultDumperPluginsBuilder(MyApplication.this)
                        .provide(new FrescoDumperPlugin()).finish();
            }

            @Nullable
            @Override
            protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
                return new Stetho.DefaultInspectorModulesBuilder(MyApplication.this).finish();
            }
        });
    }
}
