package gallery.image.oonuma.miyuki.jp.imagegallery.presenter;

/**
 * Interface representing a Presenter as model view presenter (MVP) pattern.
 */
public interface Presenter {

    /**
     * Control the lifecycle of the view as onResume().
     */
    void onResume();

    /**
     * Control the lifecycle of the view as onPause().
     */
    void onPause();
}
