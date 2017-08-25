package com.techdb.people_rxflux.action;

import com.techdb.rxflux.action.RxAction;

public interface Actions {

    String FETCH_PEOPLE = "fetch_people";

    void getPeopleList();

    boolean retry(RxAction action);
}
