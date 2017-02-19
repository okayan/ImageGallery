package jp.miyuki.oonuma.imagegallery.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jp.miyuki.oonuma.imagegallery.R;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;

/**
 *
 */
public class ImageGalleryAdapter extends android.support.v7.widget.RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder> {

    private List<FlickrItem> flickrItem;

    private Context context;

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
        this.flickrItem = flickrItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_flickr, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(flickrItem.get(position).getDescription());
        Uri uri = Uri.parse(flickrItem.get(position).getPictureUrl());
        Uri.Builder builder = uri.buildUpon();
        AsyncTaskHttpRequest task = new AsyncTaskHttpRequest(holder.imageView);
        task.execute(builder);
    }

    public class AsyncTaskHttpRequest extends AsyncTask<Uri.Builder, Void, Bitmap>{
        private ImageView imageView;

        public AsyncTaskHttpRequest(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Uri.Builder... builder){
            // 受け取ったbuilderでインターネット通信する
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            Bitmap bitmap = null;

            try{

                URL url = new URL(builder[0].toString());
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                inputStream = connection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            }catch (MalformedURLException exception){

            }catch (IOException exception){

            }finally {
                if (connection != null){
                    connection.disconnect();
                }
                try{
                    if (inputStream != null){
                        inputStream.close();
                    }
                }catch (IOException exception){
                }
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            // インターネット通信して取得した画像をImageViewにセットする
            this.imageView.setImageBitmap(result);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
