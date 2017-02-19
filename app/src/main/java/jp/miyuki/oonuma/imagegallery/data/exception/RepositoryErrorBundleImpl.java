package jp.miyuki.oonuma.imagegallery.data.exception;

import jp.miyuki.oonuma.imagegallery.domain.exception.ErrorBundle;

public class RepositoryErrorBundleImpl implements ErrorBundle {

    private Exception exception;

    public RepositoryErrorBundleImpl(Exception exception) {
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
