package jp.miyuki.oonuma.imagegallery.data.exception;

import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;

public class RepositoryErrorBundle implements ErrorBundle {

    private final Exception exception;

    public RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != exception) {
            this.exception.getMessage();
        }
        return message;
    }
}
