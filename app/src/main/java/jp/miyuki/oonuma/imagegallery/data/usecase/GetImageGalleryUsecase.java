package jp.miyuki.oonuma.imagegallery.data.usecase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import jp.miyuki.oonuma.imagegallery.data.exception.FlickrFeedException;
import jp.miyuki.oonuma.imagegallery.data.exception.NetworkConnectionException;
import jp.miyuki.oonuma.imagegallery.data.exception.RepositoryErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;
import jp.miyuki.oonuma.imagegallery.domain.usecase.UseCase;

public class GetImageGalleryUseCase implements UseCase {
    private final FlickrRepository flickerRepository;
    private final FlickrRepository.FlickrCallback callback =
            new FlickrRepository.FlickrCallback() {
                @Override
                public void onError(ErrorBundle errorBundle) {
                    flickrCallback.onError(errorBundle);
                }

                @Override
                public void onFlickrDataLoaded(ArrayList<FlickrItem> flickrs) {
                    if (flickrs == null) {
                        // TODO Exception
                        onError(new RepositoryErrorBundle(new NetworkConnectionException()));
                        return;
                    }
                    flickrCallback.onFlickrDataLoaded(flickrs);
                }
            };

    private FlickrRepository.FlickrCallback flickrCallback;

    private AsyncTask asyncTask = new AsyncTask<Object, Object, Void>() {
        @Override
        protected Void doInBackground(Object... params) {
            final FlickrRepository.FlickrCallback asyncCallBack = (FlickrRepository.FlickrCallback)params[0];
            try {
                flickerRepository.fetchFlickr(asyncCallBack);
            } catch (FlickrFeedException e) {
                asyncCallBack.onError(new RepositoryErrorBundle(e));
            } catch (NetworkConnectionException e) {
                asyncCallBack.onError(new RepositoryErrorBundle(e));
            }
            return null;
        }
    };

    public GetImageGalleryUseCase(@NonNull FlickrRepository repository) {
        flickerRepository = repository;
    }

    @Override
    public void execute(Context context, FlickrRepository.FlickrCallback flickrCallback) {
        this.flickrCallback = flickrCallback;
        asyncTask.execute(callback);
    }

}
