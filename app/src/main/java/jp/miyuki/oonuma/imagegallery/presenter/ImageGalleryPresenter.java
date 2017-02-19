package jp.miyuki.oonuma.imagegallery.presenter;

import android.os.Handler;

import java.util.ArrayList;

import javax.inject.Inject;

import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;
import jp.miyuki.oonuma.imagegallery.data.usecase.GetImageGelleryUsecase;

/**
 *
 */
public class ImageGalleryPresenter implements MainContract.Presenter {

    private MainContract.View view;

    private GetImageGelleryUsecase getImageGelleryUsecase;

    @Inject
    public ImageGalleryPresenter(GetImageGelleryUsecase getImageGelleryUsecase) {
        this.getImageGelleryUsecase = getImageGelleryUsecase;
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

    private final Handler handler = new Handler();

    @Override
    public void fetch() {
        getImageGelleryUsecase.execute(view.getContext(), new FlickrRepository.FlickrCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                view.showEmpty();
            }

            @Override
            public void onFlickrDataLoaded(final ArrayList<FlickrItem> flickrs) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                try{
                                    view.setAdapter(flickrs);
                                } catch (Exception e) {
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
