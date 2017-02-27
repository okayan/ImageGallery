package jp.miyuki.oonuma.imagegallery.presenter;

import android.os.Handler;

import java.util.ArrayList;

import javax.inject.Inject;

import jp.miyuki.oonuma.imagegallery.data.usecase.GetImageGalleryUseCase;
import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;

/**
 *
 */
public class ImageGalleryPresenter implements MainContract.Presenter {

    private MainContract.View view;

    private GetImageGalleryUseCase getImageGalleryUseCase;

    @Inject
    public ImageGalleryPresenter(GetImageGalleryUseCase getImageGalleryUseCase) {
        this.getImageGalleryUseCase = getImageGalleryUseCase;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
        view.setPresenter(this);
        fetch();
    }


    private class CallBack implements FlickrRepository.FlickrCallback {

        @Override
        public void onError(ErrorBundle errorBundle) {
            view.showEmpty();
        }

        @Override
        public void onFlickrDataLoaded(final ArrayList<FlickrItem> flickrs) {
            handler.post(new Runnable() {
                public void run() {
                    view.setAdapter(flickrs);
                }
            });
        }
    }

    private final Handler handler = new Handler();

    @Override
    public void fetch() {
        getImageGalleryUseCase.execute(view.getContext(), new CallBack());
    }
}
