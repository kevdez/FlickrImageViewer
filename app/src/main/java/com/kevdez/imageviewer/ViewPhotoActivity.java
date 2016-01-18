package com.kevdez.imageviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewPhotoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        FlickrPhotoModel flickrPhoto = (FlickrPhotoModel) intent.getSerializableExtra(PHOTO_TRANSFER);
        ImageView imageView = (ImageView) findViewById(R.id.photo_image);
        Picasso.with(this).load(flickrPhoto.getImageUrl().replaceFirst("_m.", "_b."))
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

}
