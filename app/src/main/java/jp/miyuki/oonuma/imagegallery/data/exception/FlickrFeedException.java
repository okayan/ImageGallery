package jp.miyuki.oonuma.imagegallery.data.exception;

public class FlickrFeedException extends Exception {

    public FlickrFeedException() {
        super();
    }

    public FlickrFeedException(final String message) {
        super(message);
    }

    public FlickrFeedException(final String message, Throwable cause) {
        super(message, cause);
    }

    public FlickrFeedException(final Throwable cause) {
        super(cause);
    }
}
