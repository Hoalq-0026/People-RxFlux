package com.techdb.rxflux.dispatcher;

import android.util.ArrayMap;

import com.techdb.rxflux.action.RxAction;
import com.techdb.rxflux.action.RxError;
import com.techdb.rxflux.store.RxStoreChange;
import com.techdb.rxflux.util.LoggerManager;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by le.quang.hoa on 12/12/16.
 */

public class Dispatcher {

    private static Dispatcher sInstance;
    private final RxBus mBus;
    private final LoggerManager mLogger;

    private ArrayMap<String, Subscription> mRxActionMap;
    private ArrayMap<String, Subscription> mRxStoreMap;

    private Dispatcher(RxBus bus) {
        this.mBus = bus;
        this.mRxActionMap = new ArrayMap<>();
        this.mRxStoreMap = new ArrayMap<>();
        this.mLogger = new LoggerManager();
    }

    public static synchronized Dispatcher getInstance(RxBus bus) {
        if (sInstance == null) {
            sInstance = new Dispatcher(bus);
        }
        return sInstance;
    }

    public <T extends RxActionDispatch> void subscribeRxStore(final T object) {
        final String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            mLogger.logRxStoreRegister(tag);
            mRxActionMap.put(tag, mBus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object obj) {
                    return obj instanceof RxAction;
                }
            }).subscribe(new Action1<Object>() {
                @Override
                public void call(Object obj) {
                    mLogger.logRxAction(tag, (RxAction) obj);
                    object.onRxAction((RxAction) obj);
                }
            }));
        }
    }

    public <T extends RxViewDispatch> void subscribeRxError(final T object) {
        final String tag = object.getClass().getSimpleName() + ".error";
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            mRxActionMap.put(tag, mBus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object obj) {
                    return obj instanceof RxError;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
                @Override
                public void call(Object obj) {
                    mLogger.logRxError(tag, (RxError) obj);
                    object.onRxError((RxError) obj);
                }
            }));
        }
    }

    public <T extends RxViewDispatch> void subscribeRxView(final T object) {
        final String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxStoreMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            mLogger.logViewRegisterToStore(tag);
            mRxStoreMap.put(tag, mBus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object obj) {
                    return obj instanceof RxStoreChange;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
                @Override
                public void call(Object obj) {
                    mLogger.logRxStore(tag, (RxStoreChange) obj);
                    object.onRxStoreChanged((RxStoreChange) obj);
                }
            }));
        }
    }

    public <T extends RxActionDispatch> void unsubscribeRxStore(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            mRxActionMap.remove(tag);
            mLogger.logUnregisterRxAction(tag);
        }
    }

    public <T extends RxViewDispatch> void unsubscribeRxError(final T object) {
        String tag = object.getClass().getSimpleName() + ".error";
        Subscription subscription = mRxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            mRxActionMap.remove(tag);
        }
    }

    public <T extends RxViewDispatch> void unsubscribeRxView(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = mRxStoreMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            mRxStoreMap.remove(tag);
            mLogger.logUnregisterRxStore(tag);
        }
        unsubscribeRxError(object);
    }

    public synchronized void unsubscribeAll() {
        for (Subscription subscription : mRxActionMap.values()) {
            subscription.unsubscribe();
        }

        for (Subscription subscription : mRxStoreMap.values()) {
            subscription.unsubscribe();
        }

        mRxActionMap.clear();
        mRxStoreMap.clear();
    }

    public void postRxAction(final RxAction action) {
        mBus.send(action);
    }

    public void postRxStoreChange(final RxStoreChange storeChange) {
        mBus.send(storeChange);
    }
}
