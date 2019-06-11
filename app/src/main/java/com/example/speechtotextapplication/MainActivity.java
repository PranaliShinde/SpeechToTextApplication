package com.example.speechtotextapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
TextView textView,speakBtn;
final int REQ_CODE_SR=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv);
        speakBtn=findViewById(R.id.btnSpeak);
        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This is for calling inbuilt Speech Recognizer
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);//implicit intent
                //To invoke language support and language form
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.EXTRA_LANGUAGE_MODEL);
                //Give local support to Speech Recognizer
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                //its my speech recognizer
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"BOL HALKE HALKE");
    startActivityForResult(intent,REQ_CODE_SR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQ_CODE_SR:
            {
                if(resultCode==RESULT_OK && null!=data)
                {
                    ArrayList<String>stringArrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(stringArrayList.get(0));
                }

            }
                break;
        }
    }
}
