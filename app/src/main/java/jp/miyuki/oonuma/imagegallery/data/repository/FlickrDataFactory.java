package jp.miyuki.oonuma.imagegallery.data.repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.net.Uri;
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

import jp.miyuki.oonuma.imagegallery.domain.model.FlickrItem;

/**
 *
 */
public class FlickrDataFactory {
    public static final String TAG = "FlickrDataFactory";

    private static final String ENDPOINT = "https://api.flickr.com/services/feeds/photos_public.gne?format=json";


    public String loadJSON() {
        String response = null;
        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
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


    public ArrayList<FlickrItem> downloadGalleryItems(String url) {
        ArrayList<FlickrItem> items = new ArrayList<>();
        try {
            String json = loadJSON();
            Log.i(TAG, "Received xml: " + json);
            JSONArray eventArray = rootObject.getJSONArray("{jsonFlickrFeed}");
            for (int i = 0; i < eventArray.length(); i++) {
                JSONObject jsonObject = eventArray.getJSONObject(i);
                Log.d("JSONSampleActivity", jsonObject.getString("title"));
                String title = jsonObject.getString("title");
                String link = jsonObject.getString("link");

                FlickrItem item = new FlickrItem();
                item.setTitle(title);
                item.setLink(link);


                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    void parseItems(ArrayList<FlickrItem> items, XmlPullParser parser)
            throws XmlPullParserException, IOException {

        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {
//            if (eventType == XmlPullParser.START_TAG &&
//                    XML_PHOTO.equals(parser.getName())) {
//                String id = parser.getAttributeValue(null, "id");
//                String caption = parser.getAttributeValue(null, "title");
//                String smallUrl = parser.getAttributeValue(null, EXTRA_SMALL_URL);
//                String owner = parser.getAttributeValue(null, "owner");
//
//                FlickrItem item = new FlickrItem();
////                item.setTitle(id);
//                items.add(item);
//
//            }
            eventType = parser.next();
        }
    }

    public ArrayList<FlickrItem> fetchItems() {

        String url = Uri.parse(ENDPOINT).buildUpon()
                .build().toString();
        return downloadGalleryItems(url);
    }

    public ArrayList<FlickrItem> search(String query) {
        // TODO
        return null;
    }
}
