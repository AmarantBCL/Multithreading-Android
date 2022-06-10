package com.example.android.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.multithreading.threads.CounterAsyncTaskImpl;
import com.example.android.multithreading.threads.MyAsyncTaskEvents;
import com.example.android.multithreading.threads.MySimpleAsyncTask;

public class ThreadsActivity extends AppCompatActivity implements MyAsyncTaskEvents {
    private TextView textView;
    private MySimpleAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        textView = findViewById(R.id.tv_counter);
        Button createBtn = findViewById(R.id.btn_create);
        Button startBtn = findViewById(R.id.btn_start);
        Button cancelBtn = findViewById(R.id.btn_cancel);
        createBtn.setOnClickListener(v -> doAsyncTaskCreate());
        startBtn.setOnClickListener(v -> doAsyncTaskStart());
        cancelBtn.setOnClickListener(v -> doAsyncTaskCancel());
    }

    private void doAsyncTaskCreate() {
        Toast.makeText(this, getString(R.string.on_create_task), Toast.LENGTH_SHORT).show();
        asyncTask = new CounterAsyncTaskImpl(this);
    }

    private void doAsyncTaskStart() {
        if (asyncTask == null || asyncTask.isCancelled()) {
            Toast.makeText(this, getString(R.string.on_start_task), Toast.LENGTH_SHORT).show();
        } else {
            asyncTask.execute();
        }
    }

    private void doAsyncTaskCancel() {
        asyncTask.cancel();
    }

    /***
     // IAsyncTaskEvent's methods - start:
     ***/

    @Override
    public void onPreExecute() {
        Toast.makeText(this, getString(R.string.on_pre_execute_task), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText(this, getString(R.string.on_post_execute_task), Toast.LENGTH_SHORT).show();
        textView.setText(R.string.done);
    }

    @Override
    public void onProgressUpdate(Integer integer) {
        textView.setText(String.valueOf(integer));
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, getString(R.string.on_cancel_task), Toast.LENGTH_SHORT).show();
    }

    /***
     //  IAsyncTaskEvent's methods - end
     ***/

    @Override
    protected void onDestroy() {
        if (asyncTask != null) {
            asyncTask.cancel();
            asyncTask = null;
        }
        super.onDestroy();
    }
}