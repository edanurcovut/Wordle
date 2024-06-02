package com.edanurcovut.wordle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Random;

public class off_5harf extends AppCompatActivity {

    private ImageView b_back, b_again;
    private EditText[] editTexts1, editTexts2, editTexts3, editTexts4, editTexts5;
    private CardView [] cardViews1, cardViews2, cardViews3, cardViews4, cardViews5;
    int currentEditTextIndex1 = 0,currentEditTextIndex2 = 0,currentEditTextIndex3 = 0,currentEditTextIndex4 = 0,currentEditTextIndex5 = 0, denemeHakki;
    private String[] kelimeler = {"ADANA", "AFYON", "AYDIN", "BURSA", "ÇORUM", "DÜZCE", "HATAY", "IĞDIR", "İZMİR", "KİLİS", "KONYA", "MUĞLA", "NİĞDE", "SİİRT", "SİNOP", "SİVAS", "TOKAT"};
    private String secilenKelime, secilenKelime1,secilenKelime2,secilenKelime3,secilenKelime4,secilenKelime5, olusanKelime1, olusanKelime2, olusanKelime3, olusanKelime4, olusanKelime5;
    boolean kelimeListesindeVarMi;
    StringBuilder kelimeBuilder = new StringBuilder();
    private CardView kb_e, kb_r, kb_t, kb_y, kb_u, kb_ii, kb_o, kb_p, kb_gg, kb_uu
            , kb_a, kb_s, kb_d, kb_f, kb_g, kb_h, kb_j, kb_k, kb_l, kb_ss, kb_i
            , kb_z, kb_c, kb_v, kb_b, kb_n, kb_m, kb_oo, kb_cc, kb_del, kb_entr;
    private SharedPreferences oyun;
    private SharedPreferences.Editor oyunEditor;
    private int oyunTuru, oyunSonucu, harfSayisi, sure, turuncu, yesil;
    private CountDownTimer countDownTimer;
    private long oyunSure = 60000, kalanSure = 10000;
    private boolean sureDevamEdiyor;
    private TextView tv_time;
    private Vibrator vb; // TİTREŞİM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_off5harf);

        oyun = getSharedPreferences( "WordleOyun", Context.MODE_PRIVATE );
        oyunTuru = oyun.getInt( "oyunTuru", 0 );
        oyunSonucu = oyun.getInt( "oyunSonucu", 0 );
        harfSayisi = oyun.getInt( "harfSayisi", 0 );
        sure = oyun.getInt( "sure", 0 );
        turuncu = oyun.getInt( "turuncu", 0 );
        yesil = oyun.getInt( "yesil", 0 );

        b_back = findViewById( R.id.b_back );
        b_again = findViewById( R.id.b_again );
        tv_time = findViewById( R.id.tv_time );
        tv_time.setVisibility( View.GONE );

        vb = (Vibrator) getSystemService( Context.VIBRATOR_SERVICE ); //Titreşim servisini kullanmak

        secilenKelime = rastgeleKelimeSec();

        secilenKelime1 = secilenKelime;
        secilenKelime2 = secilenKelime;
        secilenKelime3 = secilenKelime;
        secilenKelime4 = secilenKelime;
        secilenKelime5 = secilenKelime;

        denemeHakki = 1;

        harfSayisi = 5;
        oyunEditor = oyun.edit();
        oyunEditor.putInt( "harfSayisi", harfSayisi );
        oyunEditor.commit();

        sure = 0;
        oyunEditor = oyun.edit();
        oyunEditor.putInt( "sure", sure );
        oyunEditor.commit();

        yesil = 0;
        oyunEditor = oyun.edit();
        oyunEditor.putInt( "yesil", yesil );
        oyunEditor.commit();

        turuncu = 0;
        oyunEditor = oyun.edit();
        oyunEditor.putInt( "turuncu", turuncu );
        oyunEditor.commit();

        KlavyeTanimlamalari();
        EdittextTanimlamalari();
        CardViewTanimlamalari();
        hideNavigationBar();
        ButtonBack();
        ButtonAgain();
        Keyboard();
        DeleteButton();
        EnterButton();
        KolayModKontrolu();
        sureyiBaslat60();

    }
    private void KlavyeTanimlamalari(){

        kb_e = findViewById( R.id.kb_e );
        kb_r = findViewById( R.id.kb_r );
        kb_t = findViewById( R.id.kb_t );
        kb_y = findViewById( R.id.kb_y );
        kb_u = findViewById( R.id.kb_u );
        kb_ii = findViewById( R.id.kb_ii );
        kb_o = findViewById( R.id.kb_o );
        kb_p = findViewById( R.id.kb_p );
        kb_gg = findViewById( R.id.kb_gg );
        kb_uu = findViewById( R.id.kb_uu );

        kb_a = findViewById( R.id.kb_a );
        kb_s = findViewById( R.id.kb_s );
        kb_d = findViewById( R.id.kb_d );
        kb_f = findViewById( R.id.kb_f );
        kb_g = findViewById( R.id.kb_g );
        kb_h = findViewById( R.id.kb_h );
        kb_j = findViewById( R.id.kb_j );
        kb_k = findViewById( R.id.kb_k );
        kb_l = findViewById( R.id.kb_l );
        kb_ss = findViewById( R.id.kb_ss );
        kb_i = findViewById( R.id.kb_i );

        kb_z = findViewById( R.id.kb_z );
        kb_c = findViewById( R.id.kb_c );
        kb_v = findViewById( R.id.kb_v );
        kb_b = findViewById( R.id.kb_b );
        kb_n = findViewById( R.id.kb_n );
        kb_m = findViewById( R.id.kb_m );
        kb_oo = findViewById( R.id.kb_oo );
        kb_cc = findViewById( R.id.kb_cc );
        kb_del = findViewById( R.id.kb_del );
        kb_entr = findViewById( R.id.kb_entr );

    }
    private void EdittextTanimlamalari(){
        editTexts1 = new EditText[]{

                findViewById( R.id.ed_harf11 ),
                findViewById( R.id.ed_harf12 ),
                findViewById( R.id.ed_harf13 ),
                findViewById( R.id.ed_harf14 ),
                findViewById( R.id.ed_harf15 )

        };

        editTexts2 = new EditText[]{


                findViewById(R.id.ed_harf21),
                findViewById(R.id.ed_harf22),
                findViewById(R.id.ed_harf23),
                findViewById(R.id.ed_harf24),
                findViewById(R.id.ed_harf25)
        };

        editTexts3 = new EditText[]{

                findViewById(R.id.ed_harf31),
                findViewById(R.id.ed_harf32),
                findViewById(R.id.ed_harf33),
                findViewById(R.id.ed_harf34),
                findViewById(R.id.ed_harf35)
        };

        editTexts4 = new EditText[]{

                findViewById(R.id.ed_harf41),
                findViewById(R.id.ed_harf42),
                findViewById(R.id.ed_harf43),
                findViewById(R.id.ed_harf44),
                findViewById(R.id.ed_harf45)
        };

        editTexts5 = new EditText[]{

                findViewById(R.id.ed_harf51),
                findViewById(R.id.ed_harf52),
                findViewById(R.id.ed_harf53),
                findViewById(R.id.ed_harf54),
                findViewById(R.id.ed_harf55)
        };

    }
    private void CardViewTanimlamalari(){
        cardViews1 = new CardView[]{

                findViewById( R.id.cv_harf11 ),
                findViewById( R.id.cv_harf12 ),
                findViewById( R.id.cv_harf13 ),
                findViewById( R.id.cv_harf14 ),
                findViewById( R.id.cv_harf15 )

        };

        cardViews2 = new CardView[]{

                findViewById( R.id.cv_harf21 ),
                findViewById( R.id.cv_harf22 ),
                findViewById( R.id.cv_harf23 ),
                findViewById( R.id.cv_harf24 ),
                findViewById( R.id.cv_harf25 )

        };

        cardViews3 = new CardView[]{

                findViewById( R.id.cv_harf31 ),
                findViewById( R.id.cv_harf32 ),
                findViewById( R.id.cv_harf33 ),
                findViewById( R.id.cv_harf34 ),
                findViewById( R.id.cv_harf35 )

        };

        cardViews4 = new CardView[]{

                findViewById( R.id.cv_harf41 ),
                findViewById( R.id.cv_harf42 ),
                findViewById( R.id.cv_harf43 ),
                findViewById( R.id.cv_harf44 ),
                findViewById( R.id.cv_harf45 )

        };

        cardViews5 = new CardView[]{

                findViewById( R.id.cv_harf51 ),
                findViewById( R.id.cv_harf52 ),
                findViewById( R.id.cv_harf53 ),
                findViewById( R.id.cv_harf54 ),
                findViewById( R.id.cv_harf55 )

        };
    }
    private String rastgeleKelimeSec() {
        Random rastgele = new Random();
        int index = rastgele.nextInt( kelimeler.length );
        return kelimeler[index];
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
                Intent intent = new Intent(getApplicationContext(), off_mod_secimi.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    public void ButtonAgain() {
        b_again.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), off_5harf.class);
                startActivity(intent);
                finish();
            }
        } );
    }
    private void Keyboard(){

        kb_e.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("E");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("E");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("E");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("E");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("E");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_r.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("R");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("R");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("R");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("R");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("R");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_t.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("T");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("T");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("T");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("T");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("T");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_y.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Y");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Y");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Y");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Y");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Y");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_u.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("U");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("U");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("U");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("U");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("U");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_ii.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("I");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("I");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("I");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("I");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("I");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_o.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("O");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("O");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("O");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("O");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("O");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_p.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("P");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("P");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("P");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("P");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("P");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_gg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ğ");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ğ");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ğ");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ğ");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ğ");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_uu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ü");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ü");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ü");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ü");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ü");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_a.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("A");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("A");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("A");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("A");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("A");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_s.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("S");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("S");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("S");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("S");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("S");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_d.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("D");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("D");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("D");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("D");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("D");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_f.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("F");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("F");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("F");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("F");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("F");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_g.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("G");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("G");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("G");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("G");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("G");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_h.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("H");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("H");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("H");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("H");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("H");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_j.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("J");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("J");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("J");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("J");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("J");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_k.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("K");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("K");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("K");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("K");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("K");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_l.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("L");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("L");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("L");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("L");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("L");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_ss.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ş");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ş");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ş");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ş");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ş");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_i.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("İ");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("İ");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("İ");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("İ");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("İ");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_z.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Z");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Z");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Z");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Z");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Z");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_c.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("C");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("C");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("C");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("C");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("C");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_v.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("V");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("V");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("V");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("V");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("V");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("B");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("B");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("B");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("B");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("B");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_n.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("N");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("N");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("N");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("N");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("N");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_m.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("M");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("M");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("M");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("M");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("M");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_oo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ö");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ö");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ö");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ö");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ö");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

        kb_cc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    for (int i = currentEditTextIndex1; i < editTexts1.length; i++) {
                        EditText currentEditText = editTexts1[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ç");
                            currentEditTextIndex1 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts1.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 2){
                    for (int i = currentEditTextIndex2; i < editTexts2.length; i++) {
                        EditText currentEditText = editTexts2[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ç");
                            currentEditTextIndex2 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts2.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 3){
                    for (int i = currentEditTextIndex3; i < editTexts3.length; i++) {
                        EditText currentEditText = editTexts3[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ç");
                            currentEditTextIndex3 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts3.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 4){
                    for (int i = currentEditTextIndex4; i < editTexts4.length; i++) {
                        EditText currentEditText = editTexts4[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ç");
                            currentEditTextIndex4 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts4.length - 1) {
                        }
                    }
                }

                else  if (denemeHakki == 5){
                    for (int i = currentEditTextIndex5; i < editTexts5.length; i++) {
                        EditText currentEditText = editTexts5[i];
                        if (currentEditText.getText().toString().isEmpty()) {
                            currentEditText.setText("Ç");
                            currentEditTextIndex5 = i + 1; // Sonraki EditText'e geçmek için indeksi güncelle
                            break;
                        } else if (i == editTexts5.length - 1) {
                        }
                    }
                }

            }
        } );

    }
    private void DeleteButton(){
        kb_del.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){
                    if (currentEditTextIndex1 > 0) {
                        currentEditTextIndex1--;
                        // Şu anki EditText'i al
                        EditText currentEditText = editTexts1[currentEditTextIndex1];
                        // EditText'teki metni temizle
                        currentEditText.setText("");
                    }
                    else {
                        currentEditTextIndex1 = 0;
                    }
                }

                else if (denemeHakki == 2){
                    if (currentEditTextIndex2 > 0) {
                        currentEditTextIndex2--;
                        // Şu anki EditText'i al
                        EditText currentEditText = editTexts2[currentEditTextIndex2];
                        // EditText'teki metni temizle
                        currentEditText.setText("");
                    }
                    else {
                        currentEditTextIndex2 = 0;
                    }
                }

                else if (denemeHakki == 3){
                    if (currentEditTextIndex3 > 0) {
                        currentEditTextIndex3--;
                        // Şu anki EditText'i al
                        EditText currentEditText = editTexts3[currentEditTextIndex3];
                        // EditText'teki metni temizle
                        currentEditText.setText("");
                    }
                    else {
                        currentEditTextIndex3 = 0;
                    }
                }

                else if (denemeHakki == 4){
                    if (currentEditTextIndex4 > 0) {
                        currentEditTextIndex4--;
                        // Şu anki EditText'i al
                        EditText currentEditText = editTexts4[currentEditTextIndex4];
                        // EditText'teki metni temizle
                        currentEditText.setText("");
                    }
                    else {
                        currentEditTextIndex4 = 0;
                    }
                }

                else if (denemeHakki == 5){
                    if (currentEditTextIndex5 > 0) {
                        currentEditTextIndex5--;
                        // Şu anki EditText'i al
                        EditText currentEditText = editTexts5[currentEditTextIndex5];
                        // EditText'teki metni temizle
                        currentEditText.setText("");
                    }
                    else {
                        currentEditTextIndex5 = 0;
                    }
                }

            }
        } );

    }
    private void EnterButton(){
        kb_entr.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (denemeHakki == 1){


                    if (currentEditTextIndex1 < editTexts1.length){ //BOŞ KUTU KONTROLÜ

                        //KELİME TAMAMLANMADIYSA BURASI KULLANILACAK...


                    } else {


                        for (int i = 0; i < editTexts1.length; i++) {
                            kelimeBuilder.append(editTexts1[i].getText().toString().trim());
                        }

                        olusanKelime1 = kelimeBuilder.toString();
                        kelimeBuilder.setLength(0);

                        kelimeListesindeVarMi = false;

                        //BU KISIM KELİMENİN BİİM LİSTEMİZDE VAR OLUP OLMADIĞININ KONTROLÜNÜ SAĞLAR
                        for (String kelime : kelimeler) {
                            if (kelime.equalsIgnoreCase(olusanKelime1)) {//BÜYÜK KÜÇÜK HARF DUYARLILIĞI
                                kelimeListesindeVarMi = true;
                                break;
                            }
                        }

                        if (kelimeListesindeVarMi) {

                            if (olusanKelime1.equals( secilenKelime1 )){

                                //OYUNCUNUN YAZDIĞI KELİME OYUNUN RASTGELE VERDİĞİ KELİME İLE AYNIYSA
                                OyunBitirmek();
                                oyunSonucu = 1;
                                oyunEditor = oyun.edit();
                                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                                oyunEditor.commit();

                                KalanSureOgrenme();
                            }

                            //OYUNCUNUN YAZDIĞI KELİME LİSTEMİZDE VAR ANCAK RASTGELE KELİME İLE AYNI DEĞİLSE
                            else
                            {
                                if (sureDevamEdiyor) {
                                    sureyiSifirla();
                                } else {
                                    sureyiBaslat60();
                                }

                                // EŞLEŞEN VE EŞLEŞMEYEN HARFLERİ TUTACAK DİZİ
                                int[] eslesenIndexler1 = new int[secilenKelime1.length()];
                                int[] eslesmeyenIndexler1 = new int[secilenKelime1.length()];

                                // HARFLERİ KARŞILAŞTIR VE DİZİYE GÖNDER
                                for (int i = 0; i < secilenKelime1.length(); i++) {
                                    if (olusanKelime1.charAt(i) == secilenKelime1.charAt(i)) {
                                        eslesenIndexler1[i] = 1;
                                        cardViews1[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                                    }

                                    else {
                                        eslesmeyenIndexler1[i] = 1;
                                        cardViews1[i].setCardBackgroundColor(getResources().getColor(android.R.color.black));
                                    }
                                }

                                // EŞLEŞMEYEN INDEXLERLE İLGİLİ İŞLEMLER
                                for (int i = 0; i < secilenKelime1.length(); i++) {
                                    if (eslesmeyenIndexler1[i] == 1) { // EŞLEŞMEYEN INDEX
                                        for (int j = 0; j < secilenKelime1.length(); j++) {
                                            if (olusanKelime1.charAt(i) == secilenKelime1.charAt(j) && eslesenIndexler1[j] == 0) {
                                                eslesenIndexler1[j] = 1;
                                                cardViews1[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                                                break; // EŞLEŞME VARSA TURUNCU OLUR VE DÖNGÜ SONA ERER
                                            }
                                        }
                                    }
                                }




                                denemeHakki = 2;
                            }

                        }


                        else {
                            //YAZILAN KELİME BİZİM LİSTEMİZDE YOK İSE
                            Toast.makeText(getApplicationContext(), "KELİME BULUNAMADI", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                else  if (denemeHakki == 2){


                    if (currentEditTextIndex2 < editTexts2.length){ //BOŞ KUTU KONTROLÜ

                        //KELİME TAMAMLANMADIYSA BURASI KULLANILACAK...


                    } else {


                        for (int i = 0; i < editTexts2.length; i++) {
                            kelimeBuilder.append(editTexts2[i].getText().toString().trim());
                        }

                        olusanKelime2 = kelimeBuilder.toString();
                        kelimeBuilder.setLength(0);

                        kelimeListesindeVarMi = false;

                        //BU KISIM KELİMENİN BİİM LİSTEMİZDE VAR OLUP OLMADIĞININ KONTROLÜNÜ SAĞLAR
                        for (String kelime : kelimeler) {
                            if (kelime.equalsIgnoreCase(olusanKelime2)) {//BÜYÜK KÜÇÜK HARF DUYARLILIĞI
                                kelimeListesindeVarMi = true;
                                break;
                            }
                        }

                        if (kelimeListesindeVarMi) {

                            if (olusanKelime2.equals( secilenKelime2 )){

                                //OYUNCUNUN YAZDIĞI KELİME OYUNUN RASTGELE VERDİĞİ KELİME İLE AYNIYSA
                                OyunBitirmek();
                                oyunSonucu = 1;
                                oyunEditor = oyun.edit();
                                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                                oyunEditor.commit();

                                KalanSureOgrenme();
                            }

                            //OYUNCUNUN YAZDIĞI KELİME LİSTEMİZDE VAR ANCAK RASTGELE KELİME İLE AYNI DEĞİLSE
                            else
                            {
                                if (sureDevamEdiyor) {
                                    sureyiSifirla();
                                } else {
                                    sureyiBaslat60();
                                }

                                // EŞLEŞEN VE EŞLEŞMEYEN HARFLERİ TUTACAK DİZİ
                                int[] eslesenIndexler2 = new int[secilenKelime2.length()];
                                int[] eslesmeyenIndexler2 = new int[secilenKelime2.length()];

                                // HARFLERİ KARŞILAŞTIR VE DİZİYE GÖNDER
                                for (int i = 0; i < secilenKelime2.length(); i++) {
                                    if (secilenKelime2.charAt(i) == olusanKelime2.charAt(i)) {
                                        eslesenIndexler2[i] = 1;
                                        cardViews2[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                                    }

                                    else {
                                        eslesmeyenIndexler2[i] = 1;
                                        cardViews2[i].setCardBackgroundColor(getResources().getColor(android.R.color.black));
                                    }
                                }

                                // EŞLEŞMEYEN INDEXLERLE İLGİLİ İŞLEMLER
                                for (int i = 0; i < secilenKelime2.length(); i++) {
                                    if (eslesmeyenIndexler2[i] == 1) { // EŞLEŞMEYEN INDEX
                                        for (int j = 0; j < secilenKelime2.length(); j++) {
                                            if (olusanKelime2.charAt(i) == secilenKelime2.charAt(j) && eslesenIndexler2[j] == 0) {
                                                eslesenIndexler2[j] = 1;
                                                cardViews2[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                                                break; // EŞLEŞME VARSA TURUNCU OLUR VE DÖNGÜ SONA ERER
                                            }
                                        }
                                    }
                                }




                                denemeHakki = 3;
                            }

                        }


                        else {
                            //YAZILAN KELİME BİZİM LİSTEMİZDE YOK İSE
                            Toast.makeText(getApplicationContext(), "KELİME BULUNAMADI", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                else  if (denemeHakki == 3){


                    if (currentEditTextIndex3 < editTexts3.length){ //BOŞ KUTU KONTROLÜ

                        //KELİME TAMAMLANMADIYSA BURASI KULLANILACAK...


                    } else {


                        for (int i = 0; i < editTexts3.length; i++) {
                            kelimeBuilder.append(editTexts3[i].getText().toString().trim());
                        }

                        olusanKelime3 = kelimeBuilder.toString();
                        kelimeBuilder.setLength(0);

                        kelimeListesindeVarMi = false;

                        //BU KISIM KELİMENİN BİİM LİSTEMİZDE VAR OLUP OLMADIĞININ KONTROLÜNÜ SAĞLAR
                        for (String kelime : kelimeler) {
                            if (kelime.equalsIgnoreCase(olusanKelime3)) {//BÜYÜK KÜÇÜK HARF DUYARLILIĞI
                                kelimeListesindeVarMi = true;
                                break;
                            }
                        }

                        if (kelimeListesindeVarMi) {

                            if (olusanKelime3.equals( secilenKelime3 )){

                                //OYUNCUNUN YAZDIĞI KELİME OYUNUN RASTGELE VERDİĞİ KELİME İLE AYNIYSA
                                OyunBitirmek();
                                oyunSonucu = 1;
                                oyunEditor = oyun.edit();
                                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                                oyunEditor.commit();

                                KalanSureOgrenme();
                            }

                            //OYUNCUNUN YAZDIĞI KELİME LİSTEMİZDE VAR ANCAK RASTGELE KELİME İLE AYNI DEĞİLSE
                            else
                            {
                                if (sureDevamEdiyor) {
                                    sureyiSifirla();
                                } else {
                                    sureyiBaslat60();
                                }

                                // EŞLEŞEN VE EŞLEŞMEYEN HARFLERİ TUTACAK DİZİ
                                int[] eslesenIndexler3 = new int[secilenKelime3.length()];
                                int[] eslesmeyenIndexler3 = new int[secilenKelime3.length()];

                                // HARFLERİ KARŞILAŞTIR VE DİZİYE GÖNDER
                                for (int i = 0; i < secilenKelime3.length(); i++) {
                                    if (secilenKelime3.charAt(i) == olusanKelime3.charAt(i)) {
                                        eslesenIndexler3[i] = 1;
                                        cardViews3[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                                    }

                                    else {
                                        eslesmeyenIndexler3[i] = 1;
                                        cardViews3[i].setCardBackgroundColor(getResources().getColor(android.R.color.black));
                                    }
                                }

                                // EŞLEŞMEYEN INDEXLERLE İLGİLİ İŞLEMLER
                                for (int i = 0; i < secilenKelime3.length(); i++) {
                                    if (eslesmeyenIndexler3[i] == 1) { // EŞLEŞMEYEN INDEX
                                        for (int j = 0; j < secilenKelime3.length(); j++) {
                                            if (olusanKelime3.charAt(i) == secilenKelime3.charAt(j) && eslesenIndexler3[j] == 0) {
                                                eslesenIndexler3[j] = 1;
                                                cardViews3[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                                                break; // EŞLEŞME VARSA TURUNCU OLUR VE DÖNGÜ SONA ERER
                                            }
                                        }
                                    }
                                }




                                denemeHakki = 4;
                            }

                        }


                        else {
                            //YAZILAN KELİME BİZİM LİSTEMİZDE YOK İSE
                            Toast.makeText(getApplicationContext(), "KELİME BULUNAMADI", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                else  if (denemeHakki == 4){


                    if (currentEditTextIndex4 < editTexts4.length){ //BOŞ KUTU KONTROLÜ

                        //KELİME TAMAMLANMADIYSA BURASI KULLANILACAK...


                    } else {


                        for (int i = 0; i < editTexts4.length; i++) {
                            kelimeBuilder.append(editTexts4[i].getText().toString().trim());
                        }

                        olusanKelime4 = kelimeBuilder.toString();
                        kelimeBuilder.setLength(0);

                        kelimeListesindeVarMi = false;

                        //BU KISIM KELİMENİN BİİM LİSTEMİZDE VAR OLUP OLMADIĞININ KONTROLÜNÜ SAĞLAR
                        for (String kelime : kelimeler) {
                            if (kelime.equalsIgnoreCase(olusanKelime4)) {//BÜYÜK KÜÇÜK HARF DUYARLILIĞI
                                kelimeListesindeVarMi = true;
                                break;
                            }
                        }

                        if (kelimeListesindeVarMi) {

                            if (olusanKelime4.equals( secilenKelime4 )){

                                //OYUNCUNUN YAZDIĞI KELİME OYUNUN RASTGELE VERDİĞİ KELİME İLE AYNIYSA
                                OyunBitirmek();
                                oyunSonucu = 1;
                                oyunEditor = oyun.edit();
                                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                                oyunEditor.commit();

                                KalanSureOgrenme();
                            }

                            //OYUNCUNUN YAZDIĞI KELİME LİSTEMİZDE VAR ANCAK RASTGELE KELİME İLE AYNI DEĞİLSE
                            else
                            {
                                if (sureDevamEdiyor) {
                                    sureyiSifirla();
                                } else {
                                    sureyiBaslat60();
                                }

                                // EŞLEŞEN VE EŞLEŞMEYEN HARFLERİ TUTACAK DİZİ
                                int[] eslesenIndexler4 = new int[secilenKelime4.length()];
                                int[] eslesmeyenIndexler4 = new int[secilenKelime4.length()];

                                // HARFLERİ KARŞILAŞTIR VE DİZİYE GÖNDER
                                for (int i = 0; i < secilenKelime4.length(); i++) {
                                    if (secilenKelime4.charAt(i) == olusanKelime4.charAt(i)) {
                                        eslesenIndexler4[i] = 1;
                                        cardViews4[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                                    }

                                    else {
                                        eslesmeyenIndexler4[i] = 1;
                                        cardViews4[i].setCardBackgroundColor(getResources().getColor(android.R.color.black));
                                    }
                                }

                                // EŞLEŞMEYEN INDEXLERLE İLGİLİ İŞLEMLER
                                for (int i = 0; i < secilenKelime4.length(); i++) {
                                    if (eslesmeyenIndexler4[i] == 1) { // EŞLEŞMEYEN INDEX
                                        for (int j = 0; j < secilenKelime4.length(); j++) {
                                            if (olusanKelime4.charAt(i) == secilenKelime4.charAt(j) && eslesenIndexler4[j] == 0) {
                                                eslesenIndexler4[j] = 1;
                                                cardViews4[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                                                break; // EŞLEŞME VARSA TURUNCU OLUR VE DÖNGÜ SONA ERER
                                            }
                                        }
                                    }
                                }




                                denemeHakki = 5;
                            }

                        }


                        else {
                            //YAZILAN KELİME BİZİM LİSTEMİZDE YOK İSE
                            Toast.makeText(getApplicationContext(), "KELİME BULUNAMADI", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                else if (denemeHakki == 5){

                    if (currentEditTextIndex5 < editTexts5.length){
                        //Kelime Tamamlanmadıysa
                    } else {

                        for (int i = 0; i < editTexts5.length; i++) {
                            kelimeBuilder.append(editTexts5[i].getText().toString().trim());
                        }

                        olusanKelime5 = kelimeBuilder.toString();
                        kelimeBuilder.setLength(0);

                        // Oluşan kelimenin kelime listesinde olup olmadığını kontrol et
                        kelimeListesindeVarMi = false;
                        for (String kelime : kelimeler) {
                            if (kelime.equalsIgnoreCase(olusanKelime5)) {
                                kelimeListesindeVarMi = true;
                                break;
                            }
                        }

                        if (kelimeListesindeVarMi) {
                            if (olusanKelime5.equals( secilenKelime5 )){
                                //OYUNCUNUN YAZDIĞI KELİME OYUNUN RASTGELE VERDİĞİ KELİME İLE AYNIYSA
                                OyunBitirmek();
                                oyunSonucu = 1;
                                oyunEditor = oyun.edit();
                                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                                oyunEditor.commit();

                                KalanSureOgrenme();
                            }

                            else
                            {
                                // EŞLEŞEN VE EŞLEŞMEYEN HARFLERİ TUTACAK DİZİ
                                int[] eslesenIndexler5 = new int[secilenKelime5.length()];
                                int[] eslesmeyenIndexler5 = new int[secilenKelime5.length()];

                                // HARFLERİ KARŞILAŞTIR VE DİZİYE GÖNDER
                                for (int i = 0; i < secilenKelime5.length(); i++) {
                                    if (secilenKelime5.charAt(i) == olusanKelime5.charAt(i)) {
                                        eslesenIndexler5[i] = 1;
                                        cardViews5[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

                                        yesil++;
                                        oyunEditor = oyun.edit();
                                        oyunEditor.putInt( "yesil", yesil );
                                        oyunEditor.commit();
                                    }

                                    else {
                                        eslesmeyenIndexler5[i] = 1;
                                        cardViews5[i].setCardBackgroundColor(getResources().getColor(android.R.color.black));
                                    }
                                }

                                // EŞLEŞMEYEN INDEXLERLE İLGİLİ İŞLEMLER
                                for (int i = 0; i < secilenKelime5.length(); i++) {
                                    if (eslesmeyenIndexler5[i] == 1) { // EŞLEŞMEYEN INDEX
                                        for (int j = 0; j < secilenKelime5.length(); j++) {
                                            if (olusanKelime5.charAt(i) == secilenKelime5.charAt(j) && eslesenIndexler5[j] == 0) {
                                                eslesenIndexler5[j] = 1;
                                                cardViews5[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));

                                                turuncu++;
                                                oyunEditor = oyun.edit();
                                                oyunEditor.putInt( "turuncu", turuncu );
                                                oyunEditor.commit();

                                                break; // EŞLEŞME VARSA TURUNCU OLUR VE DÖNGÜ SONA ERER

                                            }
                                        }
                                    }
                                }

                                OyunBitirmek();
                                oyunSonucu = 2;
                                oyunEditor = oyun.edit();
                                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                                oyunEditor.commit();

                                KalanSureOgrenme();
                            }
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "KELİME BULUNAMADI", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        } );

    }
    private void KolayModKontrolu(){

        if (oyunTuru == 1) //KOLAY MOD SEÇİLİRSE EKRANA RASTGELE HARF VERİLİR
        {
            // RASTGELE GELEN KELİMEDEN İNDEX SEÇME
            Random random = new Random();
            int rastgeleIndex = random.nextInt(secilenKelime.length() -1);

            // İNDEXE KARŞILIK GELEN HARFİ YAZDIRMA VE RENKLENDİRME
            char rastgeleHarf = secilenKelime1.charAt(rastgeleIndex);
            editTexts1[rastgeleIndex].setText(String.valueOf(rastgeleHarf));
            cardViews1[rastgeleIndex].setCardBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }

    }
    private void sureyiBaslat60() {
        countDownTimer = new CountDownTimer(oyunSure, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setVisibility( View.GONE );
                oyunSure = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Toast.makeText( getApplicationContext(), "60 SANİYEDİR OYNAMADINIZ", Toast.LENGTH_SHORT ).show();
                vb.vibrate( 500 );
                sureyiBaslat10();
            }
        }.start();

        sureDevamEdiyor = true;
    }
    private void sureyiBaslat10() {
        countDownTimer = new CountDownTimer(kalanSure, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                kalanSure = millisUntilFinished;
                tv_time.setVisibility( View.VISIBLE );
                guncelSureyiGoster10();
            }

            @Override
            public void onFinish() {
                OyunBitirmek();
                oyunSonucu = 3;
                oyunEditor = oyun.edit();
                oyunEditor.putInt( "oyunSonucu", oyunSonucu );
                oyunEditor.commit();
            }
        }.start();

        sureDevamEdiyor = true;
    }
    private void sureyiSifirla() {
        countDownTimer.cancel();
        sureDevamEdiyor = false;
        oyunSure = 60000;
        kalanSure = 10000;
        guncelSureyiGoster10();
        sureyiBaslat60();
    }
    private void guncelSureyiGoster10() {
        int kalanSaniye = (int) (kalanSure / 1000);
        String guncelSureFormatli = String.format("%02d", kalanSaniye);
        tv_time.setText(guncelSureFormatli);
    }

    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("LastActivity", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lastActivity", getClass().getName());
        editor.apply();
    }
    private void OyunBitirmek(){
        Intent intent = new Intent(getApplicationContext(), OyunBitis.class);
        startActivity(intent);
        finish();
    }
    private void KalanSureOgrenme(){

        countDownTimer.cancel();

        sure = (int) (oyunSure / 1000);
        oyunEditor = oyun.edit();
        oyunEditor.putInt( "sure", sure );
        oyunEditor.commit();
    }



}