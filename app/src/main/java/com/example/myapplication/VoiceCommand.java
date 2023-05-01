package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.navigation.FloatingWindow;

import java.util.ArrayList;

public class VoiceCommand implements RecognitionListener {
    private Context context;
    private SpeechRecognizer speechRecognizer;

    public VoiceCommand(Context context) {
        this.context = context;
        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        this.speechRecognizer.setRecognitionListener(this);
    }

    public void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        speechRecognizer.startListening(intent);
    }

    public void stopListening() {
        speechRecognizer.stopListening();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        startListening();
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        if (matches != null && matches.size() > 0) {
            String command = matches.get(0);

            if (command != null && command.toLowerCase().equals("open layout")) {
                FloatingLayout floatingLayout = new FloatingLayout(context);
                floatingLayout.createFloatingWindow();
            } else if (command != null && command.toLowerCase().equals("close layout")) {
                FloatingLayout floatingLayout = new FloatingLayout(context);
                floatingLayout.removeFloatingWindow();
            }
        }
        startListening();
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
