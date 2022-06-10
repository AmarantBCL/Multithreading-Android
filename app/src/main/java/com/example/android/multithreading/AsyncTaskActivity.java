package com.example.android.multithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.multithreading.asynctask.AsyncTaskEvents;
import com.example.android.multithreading.asynctask.CounterAsyncTask;

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String KEY_INT = "KEY_INT";

    private TextView textView;
    private CounterAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(KEY_INT) != 0) {
                asyncTask = new CounterAsyncTask(taskEvents(), savedInstanceState.getInt(KEY_INT));
                asyncTask.execute();
            }
        }

        textView = findViewById(R.id.tv_counter);
        Button createBtn = findViewById(R.id.btn_create);
        Button startBtn = findViewById(R.id.btn_start);
        Button cancelBtn = findViewById(R.id.btn_cancel);
        createBtn.setOnClickListener(v -> createTask());
        startBtn.setOnClickListener(v -> startTask());
        cancelBtn.setOnClickListener(v -> cancelTask());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncTask.cancel(true);
        asyncTask = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (asyncTask != null) {
            outState.putInt(KEY_INT, asyncTask.getResult());
        }
    }

    private AsyncTaskEvents taskEvents() {
        return new AsyncTaskEvents() {
            @Override
            public void onPostExecute() {
                asyncTask = null;
                textView.setText(getResources().getString(R.string.done));
            }

            @Override
            public void onProgressUpdate(Integer integer) {
                textView.setText(integer + "");
            }
        };
    }

    private void createTask() {
        if (asyncTask == null || asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
            asyncTask = new CounterAsyncTask(taskEvents());
            textView.setText("Created");
        } else {
            Toast.makeText(AsyncTaskActivity.this, "Task is busy!", Toast.LENGTH_SHORT).show();
        }
    }

    private void startTask() {
        if (asyncTask != null && asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
            asyncTask.execute();
        } else {
            Toast.makeText(AsyncTaskActivity.this, "Task is not created or busy!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelTask() {
        if (asyncTask != null) {
            asyncTask.cancel(false);
            asyncTask = null;
            textView.setText("Cancelled");
        }
    }
}