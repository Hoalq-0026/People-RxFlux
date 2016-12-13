package com.techdb.rxflux.action;

import android.support.annotation.NonNull;

import com.techdb.rxflux.dispatcher.Dispatcher;
import com.techdb.rxflux.util.SubscriptionManager;

import rx.Subscription;

/**
 * Created by le.quang.hoa on 12/12/16.
 */

public abstract class RxActionCreator {

    private final Dispatcher mDispatcher;
    private final SubscriptionManager mManager;

    public RxActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        this.mDispatcher = dispatcher;
        this.mManager = manager;
    }

    public void addRxAction(RxAction action, Subscription subscription) {
        mManager.add(action, subscription);
    }

    public boolean hasRxAction(RxAction action) {
        return mManager.contains(action);
    }

    public void removeRxAction(RxAction action) {
        mManager.remove(action);
    }

    public RxAction newRxAction(@NonNull String actionId, @NonNull Object... data) {

        if (actionId.isEmpty()) {
            throw new IllegalArgumentException("Type must not be empty.");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key, value pairs");
        }

        RxAction.Builder actionBuilder = RxAction.type(actionId);
        int index = 0;
        while (index < data.length) {
            String key = (String) data[index++];
            Object value = data[index++];

            actionBuilder.bundle(key, value);
        }
        return actionBuilder.build();
    }

    public void postRxAction(@NonNull RxAction action) {
        mDispatcher.postRxAction(action);
    }

    public void postRxError(@NonNull RxAction action, Throwable throwable) {
        mDispatcher.postRxAction(RxError.newRxError(action, throwable));
    }
}
