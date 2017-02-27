package jp.miyuki.oonuma.imagegallery.domain.repository;

import java.util.ArrayList;

import jp.miyuki.oonuma.imagegallery.data.exception.FlickrFeedException;
import jp.miyuki.oonuma.imagegallery.data.exception.NetworkConnectionException;
import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;

public interface FlickrRepository {

    /**
     * Get Flickr feed.
     * @param flickrCallback
     * @throws FlickrFeedException
     * @throws NetworkConnectionException
     */
    void fetchFlickr(FlickrCallback flickrCallback) throws FlickrFeedException, NetworkConnectionException;

    interface FlickrCallback {

        void onError(ErrorBundle errorBundle);

        void onFlickrDataLoaded(ArrayList<FlickrItem> flickrs);
    }

}
