package com.techdb.people_rxflux.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("name")
    private Name mName;

    @SerializedName("location")
    private Location mLocation;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("login")
    private Login mUserName;

    @SerializedName("cell")
    private String mCell;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("picture")
    private Picture mPicture;

    private String mFullName;

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Name getName() {
        return mName;
    }

    public void setName(Name name) {
        mName = name;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Login getUserName() {
        return mUserName;
    }

    public void setUserName(Login userName) {
        mUserName = userName;
    }

    public String getCell() {
        return mCell;
    }

    public void setCell(String cell) {
        mCell = cell;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Picture getPicture() {
        return mPicture;
    }

    public void setPicture(Picture picture) {
        mPicture = picture;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public boolean hasEmail() {
        return mEmail != null && !mEmail.isEmpty();
    }
}
