package com.techdb.people_rxflux.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by le.quang.hoa on 12/12/16.
 */

public class Name implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("first")
    private String mFirst;

    @SerializedName("last")
    private String mLast;

    public Name() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getFirst() {
        return mFirst;
    }

    public void setFirst(String first) {
        mFirst = first;
    }

    public String getLast() {
        return mLast;
    }

    public void setLast(String last) {
        mLast = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (mTitle != null ? !mTitle.equals(name.mTitle) : name.mTitle != null) return false;
        if (mFirst != null ? !mFirst.equals(name.mFirst) : name.mFirst != null) return false;
        return mLast != null ? mLast.equals(name.mLast) : name.mLast == null;

    }

    @Override
    public int hashCode() {
        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = 31 * result + (mFirst != null ? mFirst.hashCode() : 0);
        result = 31 * result + (mLast != null ? mLast.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Name{" +
                "mTitle='" + mTitle + '\'' +
                ", mFirst='" + mFirst + '\'' +
                ", mLast='" + mLast + '\'' +
                '}';
    }
}
