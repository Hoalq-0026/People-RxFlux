package com.techdb.people_rxflux.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("street")
    private String mStreet;

    @SerializedName("city")
    private String mCity;

    @SerializedName("state")
    private String mState;

    @SerializedName("zip")
    private String mZip;

    public Location() {

    }

    public Location(String street, String city, String state, String zip) {
        mStreet = street;
        mCity = city;
        mState = state;
        mZip = zip;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        mStreet = street;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (mStreet != null ? !mStreet.equals(location.mStreet) : location.mStreet != null)
            return false;
        if (mCity != null ? !mCity.equals(location.mCity) : location.mCity != null) return false;
        if (mState != null ? !mState.equals(location.mState) : location.mState != null)
            return false;
        return mZip != null ? mZip.equals(location.mZip) : location.mZip == null;

    }

    @Override
    public int hashCode() {
        int result = mStreet != null ? mStreet.hashCode() : 0;
        result = 31 * result + (mCity != null ? mCity.hashCode() : 0);
        result = 31 * result + (mState != null ? mState.hashCode() : 0);
        result = 31 * result + (mZip != null ? mZip.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Location[%s, %s, %s, %s]", mCity, mState, mStreet, mZip);
    }
}
