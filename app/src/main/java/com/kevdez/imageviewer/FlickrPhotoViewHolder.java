package com.kevdez.imageviewer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FlickrPhotoViewHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;
    protected TextView imageText;

    public FlickrPhotoViewHolder(View view){
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.imageText = (TextView) view.findViewById(R.id.imageText);
    }
}
