package jp.miyuki.oonuma.imagegallery.presenter;

import android.os.Handler;

import java.util.ArrayList;

import javax.inject.Inject;

import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;
import jp.miyuki.oonuma.imagegallery.data.usecase.GetImageGalleryUsecase;

/**
 *
 */
public class ImageGalleryPresenter implements MainContract.Presenter {

    private MainContract.View view;

    private GetImageGalleryUsecase getImageGalleryUsecase;

    @Inject
    public ImageGalleryPresenter(GetImageGalleryUsecase getImageGalleryUsecase) {
        this.getImageGalleryUsecase = getImageGalleryUsecase;
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
        public void onError(/*ErrorBundle errorBundle*/) {
            view.showEmpty();
        }

        @Override
        public void onFlickrDataLoaded(final ArrayList<FlickrItem> flickrs) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            if (flickrs == null) {
                                // TODO Exception
                                onError();
                                return;
                            }
                            view.setAdapter(flickrs);
                        }
                    });
                }
            }).start();
        }
    }

    private final Handler handler = new Handler();

    @Override
    public void fetch() {
        getImageGalleryUsecase.execute(view.getContext(), new CallBack());
    }
}
