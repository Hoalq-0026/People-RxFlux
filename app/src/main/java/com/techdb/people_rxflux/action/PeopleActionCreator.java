package com.techdb.people_rxflux.action;

import com.techdb.people_rxflux.core.PeopleFactory;
import com.techdb.rxflux.action.RxAction;
import com.techdb.rxflux.action.RxActionCreator;
import com.techdb.rxflux.dispatcher.Dispatcher;
import com.techdb.rxflux.util.SubscriptionManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class PeopleActionCreator extends RxActionCreator implements Actions {

    public PeopleActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    @Override
    public void getPeopleList() {
        final String fetch_url = "http://api.randomuser.me/?results=10&nat=en";

        final RxAction action = newRxAction(FETCH_PEOPLE);
        addRxAction(action, PeopleFactory.create().fetchPeople(fetch_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        peopleResponse -> {
                            action.getData().put(Keys.PEOPLE, peopleResponse);
                            postRxAction(action);
                        },
                        throwable -> {
                            postRxError(action, throwable);
                        }));
    }

    @Override
    public boolean retry(RxAction action) {
        if (hasRxAction(action)) return true;
        switch (action.getType()) {
            case FETCH_PEOPLE:
                getPeopleList();
                return true;
            default:
                return false;
        }
    }
}
