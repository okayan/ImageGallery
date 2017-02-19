package jp.miyuki.oonuma.imagegallery.data.usecase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;
import jp.miyuki.oonuma.imagegallery.domain.usecase.UseCase;

public class GetImageGelleryUsecase extends UseCase{
    private final FlickrRepository frickerRepository;

    private AsyncTask asyncTask = new AsyncTask<Object, Object, Void>() {
        @Override
        protected Void doInBackground(Object... params) {

            frickerRepository.fetchflickr((FlickrRepository.FlickrCallback) params[0]);
            return null;
        }
    };

    public GetImageGelleryUsecase(@NonNull FlickrRepository repository) {
        frickerRepository = repository;
    }

    @Override
    public void execute(Context context, FlickrRepository.FlickrCallback flickrCallback) {
        asyncTask.execute(flickrCallback);
    }

}
