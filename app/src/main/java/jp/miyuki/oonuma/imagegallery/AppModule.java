package jp.miyuki.oonuma.imagegallery;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.miyuki.oonuma.imagegallery.data.repository.FlickrDataFactory;
import jp.miyuki.oonuma.imagegallery.data.repository.FlickrRepositoryImpl;
import jp.miyuki.oonuma.imagegallery.data.usecase.GetImageGelleryUsecase;
import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;
import jp.miyuki.oonuma.imagegallery.presenter.ImageGalleryPresenter;
import jp.miyuki.oonuma.imagegallery.presenter.MainContract;

@Module
public class AppModule {
    @Provides
    @Singleton
    public FlickrRepository provideUserRepository() {
        return new FlickrRepositoryImpl(new FlickrDataFactory());
    }

    @Provides
    public GetImageGelleryUsecase provideGetImageGelleryUsecase(FlickrRepository FlickrRepository) {
        return new GetImageGelleryUsecase(FlickrRepository);
    }

    @Provides
    public MainContract.Presenter providePresenter(GetImageGelleryUsecase usecase) {
        return new ImageGalleryPresenter(usecase);
    }
}
