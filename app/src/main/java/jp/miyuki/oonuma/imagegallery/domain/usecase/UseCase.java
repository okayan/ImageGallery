package jp.miyuki.oonuma.imagegallery.domain.usecase;

import android.content.Context;

import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;

/**
 * Use case class
 */
public interface UseCase {

    void execute(Context context, FlickrRepository.FlickrCallback callback);

}