package com.techdb.rxflux;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.techdb.rxflux.dispatcher.Dispatcher;
import com.techdb.rxflux.dispatcher.RxBus;
import com.techdb.rxflux.dispatcher.RxViewDispatch;
import com.techdb.rxflux.store.RxStore;
import com.techdb.rxflux.util.LogLevel;
import com.techdb.rxflux.util.SubscriptionManager;

import java.util.List;


public class RxFlux implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = "[RxFlux.]";
    public static LogLevel LOG_LEVEL = LogLevel.NONE;

    private static RxFlux sInstance;
    private final RxBus mBus;
    private final Dispatcher mDispatcher;
    private final SubscriptionManager mManager;
    private int mActivityCounter;

    private RxFlux(Application app) {
        this.mBus = RxBus.getInstance();
        this.mDispatcher = Dispatcher.getInstance(mBus);
        this.mManager = SubscriptionManager.getInstance();
        this.mActivityCounter = 0;
        app.registerActivityLifecycleCallbacks(this);
    }

    public static RxFlux init(Application application) {
        if (sInstance != null) throw new IllegalStateException("Init was already called.");
        return sInstance = new RxFlux(application);
    }

    public static void shutdown() {
        if (sInstance == null) return;
        sInstance.mManager.clear();
        sInstance.mDispatcher.unsubscribeAll();
    }

    public RxBus getBus() {
        return mBus;
    }

    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

    public SubscriptionManager getManager() {
        return mManager;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

        mActivityCounter++;
        if (activity instanceof RxViewDispatch) {
            List<RxStore> rxStoreList = ((RxViewDispatch) activity).getRxStoreListToRegister();
            if (rxStoreList != null) {
                for (RxStore rxStore : rxStoreList) {
                    rxStore.register();
                }
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity instanceof RxViewDispatch) {
            mDispatcher.subscribeRxView((RxViewDispatch) activity);
            ((RxViewDispatch) activity).onRxViewRegistered();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof RxViewDispatch) {
            mDispatcher.unsubscribeRxView((RxViewDispatch) activity);
            ((RxViewDispatch) activity).onRxViewUnRegistered();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityCounter--;

        if (activity instanceof RxViewDispatch) {
            List<RxStore> rxStores = ((RxViewDispatch) activity).getRxStoreListToUnRegister();
            if (rxStores != null) {
                for (RxStore rxStore : rxStores) {
                    rxStore.unregister();
                }
            }
        }

        if (mActivityCounter == 0) {
            RxFlux.shutdown();
        }
    }
}
