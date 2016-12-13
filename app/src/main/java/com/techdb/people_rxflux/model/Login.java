package com.techdb.people_rxflux.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by le.quang.hoa on 12/12/16.
 */

public class Login implements Serializable {

    @SerializedName("username")
    public String mUserName;

    public Login(){}

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        return mUserName != null ? mUserName.equals(login.mUserName) : login.mUserName == null;

    }

    @Override
    public int hashCode() {
        return mUserName != null ? mUserName.hashCode() : 0;
    }
}
