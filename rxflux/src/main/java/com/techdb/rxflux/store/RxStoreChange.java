package com.techdb.rxflux.store;

import com.techdb.rxflux.action.RxAction;

/**
 * Post a new event to notify that the store has changed.
 */

public class RxStoreChange {

    String mStoreId;
    RxAction mRxAction;

    public RxStoreChange(String storeId, RxAction action) {
        this.mStoreId = storeId;
        this.mRxAction = action;
    }

    public RxAction getRxAction() {
        return mRxAction;
    }

    public String getStoreId() {
        return mStoreId;
    }
}
