package com.techdb.rxflux.action;

import android.support.annotation.NonNull;
import android.util.ArrayMap;

public class RxError extends RxAction {

    public static final String ERROR_TYPE = "RxError.Type";
    public static final String ERROR_ACTION = "RxError.Action";
    public static final String ERROR_THROWABLE = "RxError.Throwable";

    RxError(String type, ArrayMap<String, Object> data) {
        super(type, data);
    }

    public static RxError newRxError(@NonNull RxAction rxAction, Throwable throwable) {
        ArrayMap<String, Object> data = new ArrayMap<>();
        data.put(ERROR_ACTION, rxAction);
        data.put(ERROR_THROWABLE, throwable);
        return new RxError(ERROR_TYPE, data);
    }

    public RxAction getAction() {
        return (RxAction) getData().get(ERROR_ACTION);
    }

    public Throwable getThrowable() {
        return (Throwable) getData().get(ERROR_THROWABLE);
    }
}
