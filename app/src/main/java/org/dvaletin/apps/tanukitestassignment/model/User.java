package org.dvaletin.apps.tanukitestassignment.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dvaletin on 26.06.15.
 */
public class User {
    private int id;
    @SerializedName("username")
    private String userName;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("fullname")
    private String fullName;
    private String city;
    private String country;
    @SerializedName("userpic_url")
    private String userPicUrl;
    @SerializedName("followers_count")
    private String followersCount;

    public User(int id, String userName, String firstName, String lastName, String fullName, String city, String country, String userPicUrl, String followersCount) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.city = city;
        this.country = country;
        this.userPicUrl = userPicUrl;
        this.followersCount = followersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }
}
