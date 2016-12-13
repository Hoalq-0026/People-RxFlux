package com.techdb.people_rxflux.core;

import com.google.gson.annotations.SerializedName;
import com.techdb.people_rxflux.model.People;

import java.util.ArrayList;

/**
 * Created by le.quang.hoa on 12/12/16.
 */

public class PeopleResponse {

    @SerializedName("results")
    private ArrayList<People> mPeopleList;

    public ArrayList<People> getPeopleList() {
        return mPeopleList;
    }

    public void setPeopleList(ArrayList<People> peopleList) {
        mPeopleList = peopleList;
    }
}
