package com.techdb.people_rxflux.action;

import com.techdb.rxflux.action.RxAction;

/**
 * Created by le.quang.hoa on 12/13/16.
 */

public interface Actions {

    String FETCH_PEOPLE = "fetch_people";

    void getPeopleList();

    boolean retry(RxAction action);
}
