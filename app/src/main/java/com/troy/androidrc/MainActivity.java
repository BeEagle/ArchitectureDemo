package com.troy.androidrc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.troy.androidrc.mvp.ActivityPresenter;
import com.troy.androidrc.mvp.BaseActivityPresenter;
import com.troy.androidrc.mvp.BasePresenter;
import com.troy.androidrc.view.DetailActivity;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity{


    private static String TAG = MainActivity.class.getSimpleName();

    private BaseActivityPresenter mBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBasePresenter = new ActivityPresenter();

        findViewById(R.id.btn_life).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBasePresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBasePresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBasePresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBasePresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBasePresenter.onDestroy();
    }

}
