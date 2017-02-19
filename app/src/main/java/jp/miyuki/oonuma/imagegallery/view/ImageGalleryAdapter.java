package jp.miyuki.oonuma.imagegallery.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jp.miyuki.oonuma.imagegallery.R;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;

/**
 *
 */
public class ImageGalleryAdapter extends android.support.v7.widget.RecyclerView.Adapter {

    private List<FlickrItem> FlickrItem;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView)v.findViewById(R.id.txt);
            imageView = (ImageView)v.findViewById(R.id.img);
        }
    }

    public void setflickrs(List<FlickrItem> flickrItems) {
        this.FlickrItem = flickrItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_flickr, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
