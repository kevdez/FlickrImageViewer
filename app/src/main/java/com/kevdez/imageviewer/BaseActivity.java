package com.kevdez.imageviewer;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    protected Toolbar activateToolbar() {
        if(toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.app_bar);
            if(toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }
}
