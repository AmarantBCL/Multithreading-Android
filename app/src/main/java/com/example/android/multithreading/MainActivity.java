package com.example.android.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button asyncTaskBtn = findViewById(R.id.btn_asynctask);
        Button loaderBtn = findViewById(R.id.btn_loader);
        Button threadsBtn = findViewById(R.id.btn_threads);
        asyncTaskBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class)));
        loaderBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoaderActivity.class)));
        threadsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ThreadsActivity.class)));
    }
}