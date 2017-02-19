package jp.miyuki.oonuma.imagegallery.domain.usecase;

import android.content.Context;

import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;

/**
 * Use case class
 */
public abstract class UseCase {

    protected abstract void execute(Context context, FlickrRepository.FlickrCallback flickrCallback);

}