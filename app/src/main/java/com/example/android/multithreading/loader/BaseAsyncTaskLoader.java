package com.example.android.multithreading.loader;


import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

public class BaseAsyncTaskLoader<D> extends AsyncTaskLoader<D> {
    private D data;
    private boolean isLoaded;

    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public D loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (isLoaded) {
            deliverResult(data);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(D data) {
        isLoaded = true;
        this.data = data;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        isLoaded = false;
        data = null;
        super.onReset();
    }
}
