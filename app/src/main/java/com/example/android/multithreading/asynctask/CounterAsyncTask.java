package com.example.android.multithreading.asynctask;

import android.os.AsyncTask;

import com.example.android.multithreading.asynctask.AsyncTaskEvents;

import java.util.concurrent.TimeUnit;

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Void> {
    private int result;
    private final AsyncTaskEvents events;

    public int getResult() {
        return result;
    }

    public CounterAsyncTask(AsyncTaskEvents events) {
        this.events = events;
    }

    public CounterAsyncTask(AsyncTaskEvents events, Integer integer) {
        this.events = events;
        result = integer;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        if (isCancelled()) {
            return null;
        }
        if (integers.length > 0) {
            result = integers[0];
        }
        for (int i = result; i < 10; i++) {
            result++;
            publishProgress(result);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        events.onProgressUpdate(result);
    }

    @Override
    protected void onPostExecute(Void unused) {
        events.onPostExecute();
    }
}
