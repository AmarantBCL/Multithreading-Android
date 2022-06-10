package com.example.android.multithreading.loader;

import android.content.Context;
import android.os.SystemClock;

public class CounterLoader extends BaseAsyncTaskLoader<Integer> {
    public CounterLoader(Context context) {
        super(context);
    }

    @Override
    public Integer loadInBackground() {
        int end = 10;
        for (int i = 0; i <= end; i++) {
            if (isAbandoned()) {
                return i;
            }
            SystemClock.sleep(500);
        }
        return end;
    }
}
