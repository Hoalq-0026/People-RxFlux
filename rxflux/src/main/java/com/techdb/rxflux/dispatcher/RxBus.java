package com.techdb.rxflux.dispatcher;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by le.quang.hoa on 12/12/16.
 */

public class RxBus {
    private static RxBus sInstance;

    private final Subject<Object, Object> mBus = new SerializedSubject<>(PublishSubject.create());

    private RxBus() {

    }

    public synchronized static RxBus getInstance() {
        if (sInstance == null) {
            sInstance = new RxBus();
        }
        return sInstance;
    }

    public void send(Object object) {
        mBus.onNext(object);
    }

    public Observable<Object> get() {
        return mBus;
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }
}
