package com.techdb.rxflux.dispatcher;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.techdb.rxflux.action.RxError;
import com.techdb.rxflux.store.RxStore;
import com.techdb.rxflux.store.RxStoreChange;

import java.util.List;

public interface RxViewDispatch {

    /**
     * All the stores will call this event after they process an action and the store change it.
     *
     * @param change can react and request the needed data.
     */
    void onRxStoreChanged(@NonNull RxStoreChange change);


    /**
     * Called when an error occur in some point of the flux flow.
     *
     * @param error {@link RxError} containing the information for that specific error.
     */
    void onRxError(@NonNull RxError error);

    /**
     * When an activity has been registered RxFlux will notify the activity so it can register custom views or fragments.
     */
    void onRxViewRegistered();

    /**
     * When an activity goest to stop
     * RxFlux will unregister it and the call this method so the activity can unregister custom views or fragments.
     */
    void onRxViewUnRegistered();

    @Nullable
    List<RxStore> getRxStoreListToRegister();

    @Nullable
    List<RxStore> getRxStoreListToUnRegister();
}
