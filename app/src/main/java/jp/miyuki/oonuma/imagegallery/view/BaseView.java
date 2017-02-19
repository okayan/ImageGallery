package jp.miyuki.oonuma.imagegallery.view;

import jp.miyuki.oonuma.imagegallery.presenter.BasePresenter;

/**
 *
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
