package com.kevdez.imageviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private List<FlickrPhotoModel> photosList = new ArrayList<FlickrPhotoModel>();
    private RecyclerView recyclerView;
    private FlickrPhotoViewAdapter flickrViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProcessImages processImages = new ProcessImages(true, "nature");
        processImages.execute();
        GetFlickrJSONData flickrData = new GetFlickrJSONData(true, "");
        flickrData.execute();

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
        } else if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
