package com.kevdez.imageviewer;

import java.io.Serializable;

public class FlickrPhotoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Title;
    private String ImageUrl;
    private String Author;
    private String AuthorId;
    private String Tags;

    public FlickrPhotoModel(String title, String imageUrl, String author, String authorId, String tags) {
        Title = title;
        ImageUrl = imageUrl;
        Author = author;
        AuthorId = authorId;
        Tags = tags;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return Title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public String getTags() {
        return Tags;
    }

    @Override
    public String toString() {
        return "FlickrPhotoModel{" +
                "Title='" + Title + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", Author='" + Author + '\'' +
                ", AuthorId='" + AuthorId + '\'' +
                ", Tags='" + Tags + '\'' +
                '}';
    }
}
