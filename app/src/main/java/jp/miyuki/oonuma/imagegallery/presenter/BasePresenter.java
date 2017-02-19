package jp.miyuki.oonuma.imagegallery.presenter;

/**
 * Interface representing a BasePresenter as model view presenter (MVP) pattern.
 */
public interface BasePresenter {

    /**
     * Control the lifecycle of the view as onResume().
     */
    void onResume();

    /**
     * Control the lifecycle of the view as onPause().
     */
    void onPause();
}
