package com.techdb.rxflux.dispatcher;

import com.techdb.rxflux.action.RxAction;

/**
 * The interface must be implemented by the store .
 */

public interface RxActionDispatch {

    void onRxAction(RxAction action);
}
