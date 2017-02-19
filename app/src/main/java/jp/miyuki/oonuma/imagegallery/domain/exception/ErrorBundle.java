package jp.miyuki.oonuma.imagegallery.domain.exception;

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
