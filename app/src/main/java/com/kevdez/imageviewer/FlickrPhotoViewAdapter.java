package com.kevdez.imageviewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FlickrPhotoViewAdapter extends RecyclerView.Adapter<FlickrPhotoViewHolder>{

    private Context context;
    private List<FlickrPhotoModel> photoModels;

    public FlickrPhotoViewAdapter(Context context, List<FlickrPhotoModel> photoModels) {
        this.context = context;
        this.photoModels = photoModels;
    }

    @Override
    public FlickrPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_browser,null);
        FlickrPhotoViewHolder viewHolder = new FlickrPhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FlickrPhotoViewHolder holder, int position) {
        FlickrPhotoModel photo = photoModels.get(position);
        Picasso.with(context).load(photo.getImageUrl())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);
        holder.imageText.setText(photo.getTitle() + "\nby " + photo.getAuthor() );
    }

    @Override
    public int getItemCount() {
        return (photoModels != null) ? photoModels.size() : 0;
    }
}
