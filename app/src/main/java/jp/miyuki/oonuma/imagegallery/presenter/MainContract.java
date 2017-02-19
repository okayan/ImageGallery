package jp.miyuki.oonuma.imagegallery.presenter;

import android.content.Context;

import java.util.List;

import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.view.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public class MainContract {

    public interface View extends BaseView<Presenter> {

        void setAdapter(List<FlickrItem> flickrItems);

        void showEmpty();

        Context getContext();
    }

    public interface Presenter extends BasePresenter{


        void setView(MainContract.View view);

        void fetch();

    }

}
