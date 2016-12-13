package com.techdb.rxflux.util;

import android.util.Log;

import com.techdb.rxflux.RxFlux;
import com.techdb.rxflux.action.RxAction;
import com.techdb.rxflux.action.RxError;
import com.techdb.rxflux.store.RxStoreChange;

import java.util.Locale;


/**
 * Created by le.quang.hoa on 12/8/16.
 */

public class LoggerManager {

    private int mLastActionHashCode;

    public void logRxStoreRegister(String tag) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(RxFlux.TAG, String.format(Locale.getDefault(), "RxStore %s has registered", tag));
                break;
            case NONE:
                break;
        }
    }

    public void logViewRegisterToStore(String tag) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(RxFlux.TAG, String.format(Locale.getDefault(), "View %s has registered", tag));
                break;
            case NONE:
                break;
        }
    }

    public void logRxAction(String store, RxAction action) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
                Log.d(RxFlux.TAG, String.format("Post RxAction to %s -> %s, data: %s", store, action.getType(), action.getData().toString()));
                break;
            case SIMPLIFY:
                int hashCode = action.getType().hashCode() + action.getData().hashCode();
                if (hashCode != mLastActionHashCode) {
                    mLastActionHashCode = hashCode;
                    Log.d(RxFlux.TAG, String.format("Post RxAction -> %s, data: %s", action.getType(), action.getData().toString()));
                }
                break;
            default:
                break;
        }
    }

    public void logRxStore(String store, RxStoreChange storeChange) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(RxFlux.TAG, String.format("Post RxStore change to %s -> %s action : %s", store, storeChange.getStoreId(), storeChange.getRxAction().toString()));
                break;
            case NONE:
                break;
        }
    }

    public void logUnregisterRxStore(String obj) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(RxFlux.TAG, String.format("RxStore from %s has registered", obj));
                break;
            case NONE:
                break;
        }
    }

    public void logUnregisterRxAction(String obj) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(RxFlux.TAG, String.format("RxAction from %s has registered", obj));
                break;
            case NONE:
                break;
        }
    }

    public void logRxError(String store, RxError rxError) {
        switch (RxFlux.LOG_LEVEL) {
            case FULL:
            case SIMPLIFY:
                Log.d(RxFlux.TAG, String.format("Post RxError to %s for RxAction %s", store, rxError.getAction().toString()));
                break;
            case NONE:
                break;
        }
    }
}
