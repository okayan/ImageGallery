package jp.miyuki.oonuma.imagegallery.domain.repository;

import java.util.ArrayList;

import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;

public interface FlickrRepository {

    void fetchflickr(FlickrCallback flickrCallback);

    interface FlickrCallback {

        void onError(ErrorBundle errorBundle);

        void onflickrDataLoaded(ArrayList<FlickrItem> flickrs);
    }

}
