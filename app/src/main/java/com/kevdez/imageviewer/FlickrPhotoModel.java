package com.kevdez.imageviewer;

/**
 * Created by Kevin on 1/17/2016.
 */
public class FlickrPhotoModel {
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
