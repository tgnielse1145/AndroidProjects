package com.example.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.pingpong.view.GameView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean audioState;
    ImageButton ibAudio;
    ImageButton startGame;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ibAudio = findViewById(R.id.audioOnIB);
        startGame = findViewById(R.id.playIB);
        context = this;
        sharedPreferences = getSharedPreferences("my_pref",0);
        audioState = sharedPreferences.getBoolean("audioState", true);
        if(audioState){
            ibAudio.setImageResource(R.drawable.audio_on);
        }else{
            ibAudio.setImageResource(R.drawable.audio_off);
        }

        ibAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioState){
                    audioState = false;
                    ibAudio.setImageResource(R.drawable.audio_off);
                }else{
                    audioState = true;
                    ibAudio.setImageResource(R.drawable.audio_on);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("audioState", audioState);
                editor.commit();
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameView gameView = new GameView(context);
                setContentView(gameView);
            }
        });
    }

//    public void audioPref(View view) {
//        if(audioState){
//            audioState = false;
//            ibAudio.setImageResource(R.drawable.audio_off);
//        }else{
//            audioState = true;
//            ibAudio.setImageResource(R.drawable.audio_on);
//        }
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("audioState", audioState);
//        editor.commit();
//    }
//
//    public void startGame(View view) {
//        GameView gameView = new GameView(this);
//        setContentView(gameView);
//    }
}