package jp.miyuki.oonuma.imagegallery.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import jp.miyuki.oonuma.imagegallery.MyApplication;
import jp.miyuki.oonuma.imagegallery.R;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;
import jp.miyuki.oonuma.imagegallery.presenter.MainContract;

/**
 *
 */
public class ImageGalleryFragment extends Fragment implements MainContract.View{

    private RecyclerView recyclerView;
    private ImageGalleryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Inject
    MainContract.Presenter presenter;

    public ImageGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageGalleryAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        presenter.setView(this);

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setAdapter(List<FlickrItem> flickrItems) {
        Toast.makeText(getContext(), "success", Toast.LENGTH_LONG).show();
        adapter.setflickrs(flickrItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        Toast.makeText(getContext(), "empty", Toast.LENGTH_LONG).show();
    }
}