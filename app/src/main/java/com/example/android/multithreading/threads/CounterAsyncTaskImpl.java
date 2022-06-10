package com.example.android.multithreading.threads;

import android.os.SystemClock;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class CounterAsyncTaskImpl extends MySimpleAsyncTask<Integer> {
    private final Reference<MyAsyncTaskEvents> ref;

    public CounterAsyncTaskImpl(MyAsyncTaskEvents asyncTaskEvents) {
        ref = new WeakReference<>(asyncTaskEvents);
    }

    @Override
    protected Integer doInBackground() {
        final int end = 10;
        for (int i = 0; i <= end; i++) {
            if (isCancelled()) {
                return i;
            }
            publishProgress(i);
            SystemClock.sleep(500);
        }
        return end;
    }

    @Override
    protected void onPreExecute() {
        MyAsyncTaskEvents asyncTaskEvents = ref.get();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute() {
        MyAsyncTaskEvents asyncTaskEvents = ref.get();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        MyAsyncTaskEvents asyncTaskEvents = ref.get();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onProgressUpdate(values[0]);
        }
    }
}
