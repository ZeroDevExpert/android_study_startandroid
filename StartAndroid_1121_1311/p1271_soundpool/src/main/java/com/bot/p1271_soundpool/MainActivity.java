package com.bot.p1271_soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener{

    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 5;

    SoundPool soundPool;
    int soundIdShot;
    int soundIdExplosion;

    int streamIDShot;
    int streamIDExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);
        soundIdShot = soundPool.load(this, R.raw.shot, 1);
        Log.d(LOG_TAG,"soundIdShot = " + soundIdShot);
        try {
            soundIdExplosion = soundPool.load(getAssets().openFd("explosion.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "soundIdExplosion = " + soundIdExplosion);
    }

    public void onClick(View v) {
        soundPool.play(soundIdShot, 1.0f, 0.1f, 0, 9, 1);
        soundPool.play(soundIdExplosion, 0.1f, 1, 0, 5, 1);


        //        streamIDShot = soundPool.play(soundIdShot,1,1,1,10,1);
//        Log.d(LOG_TAG, "streamIDShot = " + streamIDShot);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        soundPool.pause(streamIDShot);
        soundPool.autoPause();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        soundPool.resume(streamIDShot);
        soundPool.autoResume();
        //        streamIDExplosion = soundPool.play(soundIdExplosion, 1, 1, 0, 0, 1);
        //        Log.d(LOG_TAG, "streamIDExplosion = " + streamIDExplosion);
    }

    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG,"onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }
}
