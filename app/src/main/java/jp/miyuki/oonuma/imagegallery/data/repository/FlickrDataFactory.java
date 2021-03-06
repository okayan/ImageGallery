package jp.miyuki.oonuma.imagegallery.data.repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import jp.miyuki.oonuma.imagegallery.data.exception.FlickrFeedException;
import jp.miyuki.oonuma.imagegallery.data.exception.NetworkConnectionException;
import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;

/**
 *
 */
public class FlickrDataFactory {

    public static final String TAG = "FlickrDataFactory";

    private static final String ENDPOINT = "https://api.flickr.com/services/feeds/photos_public.gne?lang=en-us&format=json";


    public String loadJSON() throws NetworkConnectionException {
        String response = null;
        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
            throw new NetworkConnectionException(e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
            throw new NetworkConnectionException(e.getMessage());

        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("jsonFlickrFeed(")) {
                    line = line.replace("jsonFlickrFeed(", "");
                }
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    @Nullable
    public ArrayList<FlickrItem> parseFlickrItems() throws FlickrFeedException, NetworkConnectionException {
        ArrayList<FlickrItem> items = new ArrayList<>();
        try {
            String json = loadJSON();
            if (json == null) {
                return null;
            }
            Log.i(TAG, "Received xml: " + json);
            JSONObject rootObject = new JSONObject(json);
            JSONArray itemsArray = rootObject.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonObject = itemsArray.getJSONObject(i);
                Log.d("JSONSampleActivity", jsonObject.getString("title"));
                String title = jsonObject.getString("title");
                String link = jsonObject.getString("link");
                String dateTaken = jsonObject.getString("date_taken");
                String description = jsonObject.getString("description");
                String published = jsonObject.getString("published");
                String author = jsonObject.getString("author");
                String authorId = jsonObject.getString("author_id");
                String tags = jsonObject.getString("tags");
                String pictureUrl = parsePictureUrlString(jsonObject.getString("media"));

                FlickrItem item = new FlickrItem();
                item.setTitle(title);
                item.setLink(link);
                item.setDateTaken(dateTaken);
                item.setDescription(description);
                item.setPublished(published);
                item.setAuthor(author);
                item.setAuthorId(authorId);
                item.setTags(tags);
                item.setPictureUrl(pictureUrl);

                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new FlickrFeedException(e.getMessage());
        } catch (NetworkConnectionException e) {
            e.printStackTrace();
            throw new NetworkConnectionException(e.getMessage());
        }
        return items;
    }

    private String parsePictureUrlString(String media) throws FlickrFeedException {
        try {
            JSONObject rootObject = new JSONObject(media);

            return rootObject.getString("m");

        } catch (JSONException e) {
            e.printStackTrace();
            throw new FlickrFeedException("Picture url is not found.");
        }
    }

    @Nullable
    public ArrayList<FlickrItem> fetchItems() throws FlickrFeedException, NetworkConnectionException {
        return parseFlickrItems();
    }

    public ArrayList<FlickrItem> search(String query) {
        // TODO
        return null;
    }
}
