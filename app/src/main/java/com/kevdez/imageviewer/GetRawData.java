package com.kevdez.imageviewer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// URL https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1
public class GetRawData extends AsyncTask<String, Void, String> {

    private final String LOG_TAG = GetRawData.class.getSimpleName();

    private String data;

    public GetRawData() {
    }

    public String getData() {
        return data;
    }

    @Override
    protected String doInBackground(String... params) {
        if (params == null)
            return null;

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer stringBuffer = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream istream = connection.getInputStream();
            if(istream == null)
                return null;

            stringBuffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(istream));

            String s = null;
            while((s = reader.readLine()) != null) {
                stringBuffer.append(s);
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return stringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String rawData) {
        this.data = rawData;
    }
}
