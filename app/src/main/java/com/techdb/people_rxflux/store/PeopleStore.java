package com.techdb.people_rxflux.store;

import com.techdb.people_rxflux.action.Actions;
import com.techdb.people_rxflux.action.Keys;
import com.techdb.people_rxflux.core.PeopleResponse;
import com.techdb.people_rxflux.model.People;
import com.techdb.rxflux.action.RxAction;
import com.techdb.rxflux.dispatcher.Dispatcher;
import com.techdb.rxflux.store.RxStore;
import com.techdb.rxflux.store.RxStoreChange;

import java.util.ArrayList;

/**
 * Created by le.quang.hoa on 12/13/16.
 */

public class PeopleStore extends RxStore implements PeopleStoreInterface {

    public static final String ID = "PeopleList";
    private static PeopleStore sInstance;
    private ArrayList<People> mPeoples;

    public PeopleStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static synchronized PeopleStore getInstance(Dispatcher dispatcher) {
        if (sInstance == null) {
            sInstance = new PeopleStore(dispatcher);
        }
        return sInstance;
    }

    @Override
    public ArrayList<People> getPeopleList() {
        return mPeoples == null ? new ArrayList<>() : mPeoples;
    }

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.FETCH_PEOPLE:
                PeopleResponse response = ((PeopleResponse) action.getData().get(Keys.PEOPLE));
                if (response != null) {
                    this.mPeoples = response.getPeopleList();
                }
                break;
            default:
                break;
        }

        postChanged(new RxStoreChange(ID, action));
    }
}
