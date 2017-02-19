package jp.miyuki.oonuma.imagegallery.domain.usecase;

import android.content.Context;

import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;

/**
 * Use cases
 */
public abstract class UseCase {

    private UseCaseCallback useCaseCallback;

    public UseCaseCallback getUseCaseCallback() {
        return useCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
    }

//    void run() {
//        execute(new FlickrRepository.flickrCallback() {
//            @Override
//            public void onError(ErrorBundle errorBundle) {
//            }
//
//            @Override
//            public void onFlickrDataLoaded(ArrayList<FlickrItem> flickrs) {
//            }
//        });
//    }

    protected abstract void execute(Context context, FlickrRepository.FlickrCallback flickrCallback);

    /**
     * Data received from a request.
     */
    public interface ResponseValue {
    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }
}