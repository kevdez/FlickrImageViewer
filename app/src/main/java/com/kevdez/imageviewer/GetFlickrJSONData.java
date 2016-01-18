package com.kevdez.imageviewer;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetFlickrJSONData extends GetRawData {

    private final String LOG_TAG = GetFlickrJSONData.class.getSimpleName();

    private List<FlickrPhotoModel> photoList;

    private Uri uri;

    private final String baseURL = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1";

    private final String TAGS_PARAM = "tags";
    private final String TAG_MODE_PARAM = "tagmode";
    private final String FORMAT_PARAM = "format";
    //    private final String LANG_PARAM = "lang";
    private final String NO_JSON_CALLBACK_PARAM = "nojsoncallback";
    public GetFlickrJSONData(boolean matchAllTags, String searchQuery) {
        super();
        photoList = new ArrayList<FlickrPhotoModel>();
        buildFlickrURI(matchAllTags, searchQuery);
    }

    public List<FlickrPhotoModel> getPhotoList() {
        return photoList;
    }

    @Override
    protected String doInBackground(String... params) {
        return super.doInBackground(uri.toString());
    }

    @Override
    protected void onPostExecute(String rawData) {
        super.onPostExecute(rawData);
        processJSON();
    }

    private boolean buildFlickrURI(boolean matchAllTags, String searchQuery) {
        uri = Uri.parse(baseURL).buildUpon()
                .appendQueryParameter(TAGS_PARAM, searchQuery)
                .appendQueryParameter(TAG_MODE_PARAM, matchAllTags ? "ALL" : "ANY")
                .appendQueryParameter(FORMAT_PARAM, "json")
//                .appendQueryParameter(LANG_PARAM, "en-us")
                .appendQueryParameter(NO_JSON_CALLBACK_PARAM, "1")
                .build();

        return uri != null;
    }

    public void processJSON() {
        final String FLICKR_ITEMS = "items"; // JSON Array
        final String FLICKR_TITLE = "title";
        final String FLICKR_MEDIA = "media"; // JSON Object
        final String FLICKR_IMAGE_URL = "m"; // key inside the Media JSON Object
        final String FLICKR_AUTHOR = "author";
        final String FLICKR_AUTHOR_ID = "author_id";
        final String FLICKR_TAGS = "tags";

        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONArray jsonImagesArray = jsonData.getJSONArray(FLICKR_ITEMS);
            for ( int i = 0; i < jsonImagesArray.length(); i++) {
                JSONObject jsonImage = jsonImagesArray.getJSONObject(i);
                String title = jsonImage.getString(FLICKR_TITLE);
                JSONObject jsonMedia = jsonImage.getJSONObject(FLICKR_MEDIA);
                String imageURL = jsonMedia.getString(FLICKR_IMAGE_URL);
                String author = jsonImage.getString(FLICKR_AUTHOR);
                String authorId = jsonImage.getString(FLICKR_AUTHOR_ID);
                String tags = jsonImage.getString(FLICKR_TAGS);

                FlickrPhotoModel image = new FlickrPhotoModel(title, imageURL, author, authorId, tags);
                this.photoList.add(image);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

    }
}
