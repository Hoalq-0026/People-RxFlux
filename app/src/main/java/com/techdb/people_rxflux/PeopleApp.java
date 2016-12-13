package com.techdb.people_rxflux;

import android.app.Application;
import android.content.Context;

import com.techdb.people_rxflux.action.PeopleActionCreator;
import com.techdb.rxflux.RxFlux;
import com.techdb.rxflux.util.LogLevel;

/**
 * Created by le.quang.hoa on 12/13/16.
 */

public class PeopleApp extends Application {
    RxFlux mRxFlux;

    private PeopleActionCreator mPeopleActionCreator;

    public static PeopleApp get(Context context) {
        return (PeopleApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RxFlux.LOG_LEVEL = BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE;
        mRxFlux = RxFlux.init(this);
        mPeopleActionCreator = new PeopleActionCreator(mRxFlux.getDispatcher(), mRxFlux.getManager());
    }

    public RxFlux getRxFlux() {
        return mRxFlux;
    }

    public PeopleActionCreator getActionCreator() {
        return mPeopleActionCreator;
    }
}
