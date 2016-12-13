package com.techdb.rxflux.action;

import android.util.ArrayMap;

import java.util.Locale;

/**
 * Object class that hold the type action and the data we want to attach to it.
 */
public class RxAction {
    private final String mType;
    private final ArrayMap<String, Object> mData;


    RxAction(String type, ArrayMap<String, Object> data) {
        this.mType = type;
        this.mData = data;
    }

    public static Builder type(String type) {
        return new Builder().with(type);
    }

    public String getType() {
        return mType;
    }

    public ArrayMap<String, Object> getData() {
        return mData;

    }

    @SuppressWarnings("unchecked")
    public <T> T get(String tag) {
        return (T) mData.get(tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof RxAction)) return false;

        final RxAction rxAction = (RxAction) obj;

        return !(mData != null ? !mData.equals(rxAction.mData) : rxAction.mData != null);

    }

    @Override
    public int hashCode() {
        int result = mType.hashCode();
        final int prime = 31;
        result = prime * result + (mData != null ? mData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "RxAction{type: %s, data: %s}", mType, mData);
    }

    public static class Builder {
        private String mType;
        private ArrayMap<String, Object> mData;

        Builder with(String type) {
            if (type == null) {
                throw new IllegalArgumentException("Type must no be null.");
            }
            this.mType = type;
            this.mData = new ArrayMap<>();
            return this;
        }

        public Builder bundle(String key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("Key must no be null.");
            }

            if (value == null) {
                throw new IllegalArgumentException("Value must no be null.");
            }

            this.mData.put(key, value);
            return this;
        }


        public RxAction build() {
            if (mType == null || mType.isEmpty()) {
                throw new IllegalArgumentException("At least one key is required!");
            }

            return new RxAction(mType, mData);
        }
    }
}
