package com.techdb.rxflux.store;

import com.techdb.rxflux.dispatcher.Dispatcher;
import com.techdb.rxflux.dispatcher.RxActionDispatch;


public abstract class RxStore implements RxActionDispatch {

    private final Dispatcher mDispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void register() {
        mDispatcher.subscribeRxStore(this);
    }

    public void unregister() {
        mDispatcher.unsubscribeRxStore(this);
    }

    protected void postChanged(RxStoreChange change) {
        mDispatcher.postRxStoreChange(change);
    }
}
