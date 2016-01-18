package com.kevdez.imageviewer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = "MainActivity";
    private List<FlickrPhotoModel> photosList = new ArrayList<FlickrPhotoModel>();
    private RecyclerView recyclerView;
    private FlickrPhotoViewAdapter flickrViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProcessImages processImages = new ProcessImages(true, "");
        processImages.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_refresh) {
            ProcessImages processImages = new ProcessImages(true, "");
            processImages.execute();
            return true;

        }
        if (id == R.id.menu_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(flickrViewAdapter != null) {
            String query = getSavedPreferences(QUERY);
            if(query.length() > 0) {
                ProcessImages processImages = new ProcessImages(true,query);
                processImages.execute();
            }
        }
    }

    private String getSavedPreferences(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(key, "");
    }

    public class ProcessImages extends GetFlickrJSONData {

        public ProcessImages(boolean matchAllTags, String searchQuery) {
            super(matchAllTags, searchQuery);
        }

        @Override
        protected void onPostExecute(String rawData) {
            super.onPostExecute(rawData);
            flickrViewAdapter = new FlickrPhotoViewAdapter(MainActivity.this, getPhotoList());
            recyclerView.setAdapter(flickrViewAdapter);
        }
    }
}
