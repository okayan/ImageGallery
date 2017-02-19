package jp.miyuki.oonuma.imagegallery;

import android.app.Application;

public class MyApplication extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();

    }

    public AppComponent getComponent() {
        return component;
    }
}
