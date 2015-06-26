package org.dvaletin.apps.tanukitestassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dvaletin on 26.06.15.
 */
public class Picture {
    private int id;
    @SerializedName("name")
    private String title;
    @SerializedName("user")
    private User author; // = photo.getJSONObject("user").getString("fullname");

    private List<String> images;
    @Deprecated
    @SerializedName("image_url")
    private String url; // = photo.getString("image_url");

    public Picture(int id, String title, User author, List<String> images, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.images = images;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
