package com.example.android.multithreading;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.android.multithreading.loader.CounterLoader;

public class LoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Integer> {
    private static final int LOADER_COUNTER = 1;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        textView = findViewById(R.id.tv_counter);
        Button startBtn = findViewById(R.id.btn_start);
        Button cancelBtn = findViewById(R.id.btn_cancel);
        startBtn.setOnClickListener(v -> startLoader());
        cancelBtn.setOnClickListener(v -> cancelLoader());
    }

    private void startLoader() {
        getSupportLoaderManager().initLoader(LOADER_COUNTER, null, this);
    }

    private void cancelLoader() {
        getSupportLoaderManager().destroyLoader(LOADER_COUNTER);
    }

    @Override
    public Loader<Integer> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_COUNTER) {
            Toast.makeText(this, getString(R.string.on_create_loader), Toast.LENGTH_SHORT).show();
            return new CounterLoader(this);
        } else {
            throw new IllegalArgumentException("Unknown loader id");
        }
    }

    @Override
    public void onLoadFinished(Loader<Integer> loader, Integer data) {
        if (loader.getId() == LOADER_COUNTER) {
            Toast.makeText(this, getString(R.string.done), Toast.LENGTH_SHORT).show();
            textView.setText(R.string.done);
        }
    }

    @Override
    public void onLoaderReset(Loader<Integer> loader) {
        if (loader.getId() == LOADER_COUNTER) {
            textView.setText(null);
            Toast.makeText(this, getString(R.string.on_loader_reset), Toast.LENGTH_SHORT).show();
        }
    }
}