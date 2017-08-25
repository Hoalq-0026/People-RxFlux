package com.techdb.people_rxflux.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("large")
    private String mLarge;

    @SerializedName("medium")
    private String mMedium;

    @SerializedName("thumbnail")
    private String mThumbnail;

    public Picture() {
    }

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (mLarge != null ? !mLarge.equals(picture.mLarge) : picture.mLarge != null) return false;
        if (mMedium != null ? !mMedium.equals(picture.mMedium) : picture.mMedium != null)
            return false;
        return mThumbnail != null ? mThumbnail.equals(picture.mThumbnail) : picture.mThumbnail == null;

    }

    @Override
    public int hashCode() {
        int result = mLarge != null ? mLarge.hashCode() : 0;
        result = 31 * result + (mMedium != null ? mMedium.hashCode() : 0);
        result = 31 * result + (mThumbnail != null ? mThumbnail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "mLarge='" + mLarge + '\'' +
                ", mMedium='" + mMedium + '\'' +
                ", mThumbnail='" + mThumbnail + '\'' +
                '}';
    }
}
