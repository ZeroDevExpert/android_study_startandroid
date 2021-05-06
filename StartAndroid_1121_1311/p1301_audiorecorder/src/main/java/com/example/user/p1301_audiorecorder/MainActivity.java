package com.example.user.p1301_audiorecorder;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final String LOG_TAG = "myLogs";
    int myBufferSize = 8192;
    AudioRecord audioRecord;
    boolean isReading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAudioRecorder();
        Log.d(LOG_TAG, "init state = " + audioRecord.getState());
    }

    private void createAudioRecorder() {
        int samplerate = 22050;
        int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        int minInternalBufferSize = AudioRecord.getMinBufferSize(samplerate,
                channelConfig, audioFormat);
        int internalBufferSize = minInternalBufferSize * 4;

        Log.d(LOG_TAG, "minInternalBufferSize: " + minInternalBufferSize +
                ", internalBUfferSize: " + internalBufferSize +
                ", myBufferSize: " + myBufferSize);

        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                samplerate, channelConfig, audioFormat, internalBufferSize);

        audioRecord.setNotificationMarkerPosition(10000);
        audioRecord.setPositionNotificationPeriod(1000);
        audioRecord.setRecordPositionUpdateListener(new AudioRecord.OnRecordPositionUpdateListener() {
            @Override
            public void onMarkerReached(AudioRecord audioRecord) {
                Log.d(LOG_TAG, "on Marker Notification");
                isReading = false;
            }

            @Override
            public void onPeriodicNotification(AudioRecord audioRecord) {
                Log.d(LOG_TAG, "on Periodic Notification");
            }
        });
    }

    public void recordStart(View v) {
        Log.d(LOG_TAG, "record start");
        audioRecord.startRecording();
        int recordingState = audioRecord.getRecordingState();
        Log.d(LOG_TAG, "recordingState: " + recordingState);
    }

    public void recordStop(View v) {
        Log.d(LOG_TAG, "record stop");
        audioRecord.stop();
    }

    public void readStart(View v) {
        Log.d(LOG_TAG, "read start");
        isReading = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (audioRecord == null) {
                    return;
                }
                byte[] myBuffer = new byte[myBufferSize];
                int readCount = 0;
                int totalCount = 0;
                while (isReading) {
                    readCount = audioRecord.read(myBuffer, 0, myBufferSize);
                    totalCount += readCount;
                    Log.d(LOG_TAG, "readCount: " + readCount + ", totalCount: " + totalCount);
                }
            }
        }).start();
    }

    public void readStop(View v) {
        Log.d(LOG_TAG, "read stop");
        isReading = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        isReading = false;
        if (audioRecord != null) {
            audioRecord.release();
        }
    }
}
