package jp.miyuki.oonuma.imagegallery.domain.repository;

import java.util.Collection;

import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrModel;

public interface FrickrRepository {

    void fetchFrickr();

    interface FrickrCallback {

        void onError(ErrorBundle errorBundle);

        void onFrickrDataLoaded(Collection<FlickrModel> flickrs);
    }

}
