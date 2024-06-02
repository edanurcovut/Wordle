package com.edanurcovut.wordle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class off_mod_secimi extends AppCompatActivity {

    private ImageView b_back;
    private CardView b_4harf, b_5harf, b_6harf, b_7harf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_off_mod_secimi );

            b_back = findViewById( R.id.b_back );
            b_4harf = findViewById( R.id.b_4harf );
            b_5harf = findViewById( R.id.b_5harf );
            b_6harf = findViewById( R.id.b_6harf );
            b_7harf = findViewById( R.id.b_7harf );


            hideNavigationBar();
            ButtonBack();
            Button4Harf();
            Button5Harf();
            Button6Harf();
            Button7Harf();

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
                Intent intent = new Intent(getApplicationContext(), off_tur_secimi.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    public void Button4Harf() {
        b_4harf.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), off_4harf.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    public void Button5Harf() {
        b_5harf.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), off_5harf.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    public void Button6Harf() {
        b_6harf.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), off_6harf.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    public void Button7Harf() {
        b_7harf.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), off_7harf.class);
                startActivity(intent);
                finish();
            }
        } );
    }

}