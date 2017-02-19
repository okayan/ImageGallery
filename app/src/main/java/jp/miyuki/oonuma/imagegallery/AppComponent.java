package jp.miyuki.oonuma.imagegallery;

import javax.inject.Singleton;

import dagger.Component;
import jp.miyuki.oonuma.imagegallery.view.ImageGalleryFragment;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    void inject(ImageGalleryFragment target);
}
