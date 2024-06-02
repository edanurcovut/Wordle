package com.edanurcovut.wordle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OyunBitis extends AppCompatActivity {

    private TextView tv_sonuc, tv_skor;
    private CardView b_anasayfa, b_tekrar;
    private SharedPreferences oyun;
    private SharedPreferences.Editor oyunEditor;
    private int oyunSonucu, skor, harfSayisi, sure, turuncu, yesil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_oyun_bitis );

        oyun = getSharedPreferences( "WordleOyun", Context.MODE_PRIVATE );
        oyunSonucu = oyun.getInt( "oyunSonucu", 0 );
        harfSayisi = oyun.getInt( "harfSayisi", 0 );
        sure = oyun.getInt( "sure", 0 );
        turuncu = oyun.getInt( "turuncu", 0 );
        yesil = oyun.getInt( "yesil", 0 );

        tv_sonuc = findViewById( R.id.tv_sonuc );
        tv_skor = findViewById( R.id.tv_skor );
        b_anasayfa = findViewById( R.id.b_anasayfa );
        b_tekrar = findViewById( R.id.b_tekrar );


        hideNavigationBar();
        Anasayfa();
        Tekrar();
        oyunDurumunuBelirle();
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
    private void Anasayfa(){
        b_anasayfa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    private void Tekrar(){
        b_tekrar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("LastActivity", MODE_PRIVATE);
                String lastActivity = preferences.getString("lastActivity", "");
                if (!lastActivity.isEmpty()) {
                    try {
                        Class<?> lastActivityClass = Class.forName(lastActivity);
                        Intent intent = new Intent(getApplicationContext(), lastActivityClass);
                        startActivity(intent);
                        finish(); // Bu, mevcut activity'yi kapatmak için kullanılır, geri butonu ile tekrar bu activity'e dönmemesi için.
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } );
    }
    private void oyunDurumunuBelirle(){

        if (oyunSonucu == 1){
            tv_sonuc.setText( "KAZANDINIZ" );
            tv_sonuc.setTextColor(Color.parseColor("#169D53"));

            skor = (harfSayisi * 10) + sure;
            tv_skor.setText( String.valueOf( skor ) );

        }
        else if (oyunSonucu == 2){
            tv_sonuc.setText( "KAYBETTİNİZ" );
            tv_sonuc.setTextColor(Color.parseColor("#DC2929"));

            skor = ((yesil * 10) + (turuncu * 5) + sure );
            tv_skor.setText( String.valueOf( skor ) );
        }
        else if (oyunSonucu == 3){
            tv_sonuc.setText( "ZAMAN DOLDU" );
            tv_sonuc.setTextColor(Color.parseColor("#AFA9A9"));

            skor = 0;
            tv_skor.setText( String.valueOf( skor ) );
        }
    }
}