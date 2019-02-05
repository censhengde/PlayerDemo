package com.example.playerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private PlayHolder mPlayHolder;
    private static final String DATA_SOURCE ="http:/......";//网络视频来源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = findViewById(R.id.surfaceView);
        mPlayHolder = new PlayHolder();
        mPlayHolder.setSurfaceView(mSurfaceView);
        mPlayHolder.setOnPrepareListener(new PlayHolder.OnPrepareListener() {
            @Override
            public void onPrepare() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
                    }
                });
                mPlayHolder.start();
            }

        });

        mPlayHolder.setDataSource(DATA_SOURCE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayHolder.prepare();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlayHolder.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayHolder.release();
    }
}
