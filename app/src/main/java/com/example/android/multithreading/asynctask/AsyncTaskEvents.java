package com.example.android.multithreading.asynctask;

public interface AsyncTaskEvents {
    void onPostExecute();

    void onProgressUpdate(Integer integer);
}
