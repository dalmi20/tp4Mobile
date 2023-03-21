package com.example.takennotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoteDetails extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    TextView textView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        textView = findViewById(R.id.note);
        textView1 = findViewById(R.id.date);
        ImageView button = findViewById(R.id.synthese);

        Date currentDate = Calendar.getInstance().getTime();

        textView1.setText(currentDate.toString());

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language not supported");
                    }
                }else{
                    Log.e("TTS","Language not supported");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(String.valueOf(textView.getText()),0,null);
            }
        });

        Intent intent = this.getIntent();

        if (intent != null){
            String note = intent.getStringExtra("note");
            textView.setText(note);
        }



    }
    @Override
    protected void onDestroy() {
        if (textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        if (textToSpeech != null){
            textToSpeech.stop();

        }
        super.onPause();
    }

}