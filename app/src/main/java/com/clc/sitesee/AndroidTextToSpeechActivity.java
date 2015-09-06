package com.clc.sitesee;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

/**
 * Created by Cindy on 9/5/2015.
 */
public class AndroidTextToSpeechActivity extends Activity implements
        TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {
    /**
     * Called when the activity is first created.
     */

    private TextToSpeech tts;
    private String text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts = new TextToSpeech(this, this);
        text = getIntent().getStringExtra("list");
        Log.v("TextToSpeech", "In onCreate");
    }

    @Override
    public void onDestroy() {
        Log.v("text finished", "In onDestroy");
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void onUtteranceCompleted(String utteranceId) {
        finish();
    }

    @Override
    public void onInit(int status) {
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {
                Log.v("Finished1!!", "FINISHSINGINSDING");
                finish();
                Log.v("Finished2!!", "FINISHSINGINSDING");
            }

            @Override
            public void onError(String utteranceId) {

            }
        });

        Log.v("TextToSpeech", "In onInit");
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 10s
                Log.v("delay done", "finish");
                finish();
            }
        }, 10000);
    }
}
