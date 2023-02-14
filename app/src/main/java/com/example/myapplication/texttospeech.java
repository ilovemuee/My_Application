package com.example.myapplication;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class texttospeech {
    Context context;
    texttospeech(Context context){
        this.context = context;
    }
    TextToSpeech tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported");
                }
            } else {
                Log.e("TTS", "Initialization failed");
            }
        }
    });
    public void texttospeech(String message){
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
}