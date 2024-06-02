package com.edanurcovut.wordle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class off_tur_secimi extends AppCompatActivity {

    private ImageView b_back;
    private CardView b_kolay, b_zor;

    private SharedPreferences oyun;
    private SharedPreferences.Editor oyunEditor;
    private int oyunTuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_off_tur_secimi );

        oyun = getSharedPreferences( "WordleOyun", Context.MODE_PRIVATE );

        oyunTuru = 0;
        oyunEditor = oyun.edit();
        oyunEditor.putInt( "oyunTuru", oyunTuru );
        oyunEditor.commit();

        b_back = findViewById( R.id.b_back );
        b_kolay = findViewById( R.id.b_kolay );
        b_zor = findViewById( R.id.b_zor );

        hideNavigationBar();
        ButtonBack();
        ButtonKolay();
        ButtonZor();

    }

    private void hideNavigationBar() {
        this.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                );
    }
    private void ButtonBack() {
        b_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    private void ButtonKolay(){

        b_kolay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyunTuru = 1;
                oyunEditor = oyun.edit();
                oyunEditor.putInt( "oyunTuru", oyunTuru );
                oyunEditor.commit();

                Intent intent = new Intent(getApplicationContext(), off_mod_secimi.class);
                startActivity(intent);
                finish();
            }
        } );

    }
    private void ButtonZor(){
        b_zor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyunTuru = 2;
                oyunEditor = oyun.edit();
                oyunEditor.putInt( "oyunTuru", oyunTuru );
                oyunEditor.commit();

                Intent intent = new Intent(getApplicationContext(), off_mod_secimi.class);
                startActivity(intent);
                finish();
            }
        } );

    }


}