package jp.miyuki.oonuma.imagegallery.data.repository;

import java.util.ArrayList;

import jp.miyuki.oonuma.imagegallery.data.exception.FlickrFeedException;
import jp.miyuki.oonuma.imagegallery.data.exception.NetworkConnectionException;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;

/**
 *
 */
public class FlickrRepositoryImpl implements FlickrRepository {

    private FlickrDataFactory flickrDataFactory;

    public FlickrRepositoryImpl(FlickrDataFactory flickrDataFactory) {
        this.flickrDataFactory = flickrDataFactory;
    }

    @Override
    public void fetchFlickr(final FlickrCallback flickrCallback) throws FlickrFeedException, NetworkConnectionException {
        final ArrayList<FlickrItem> flickrItemList = this.flickrDataFactory.fetchItems();
        flickrCallback.onFlickrDataLoaded(flickrItemList);
    }
}
