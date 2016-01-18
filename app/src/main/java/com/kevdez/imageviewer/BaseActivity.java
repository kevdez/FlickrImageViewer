package com.kevdez.imageviewer;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String QUERY = "QUERY";

    protected Toolbar activateToolbar() {
        if(toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.app_bar);
            if(toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }

    protected Toolbar activateToolbarWithHomeEnabled() {
        activateToolbar();
        if(toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return toolbar;
    }
}
