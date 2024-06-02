package com.edanurcovut.wordle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText ed_mail, ed_sifre;
    CardView bGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        bGiris = findViewById( R.id.bGiris );
        ed_mail = findViewById( R.id.ed_mail );
        ed_sifre = findViewById( R.id.ed_sifre );

        hideNavigationBar();
        ButtonGiris();

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
    private void ButtonGiris()
    {
        bGiris.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = ed_mail.getText().toString().trim();
                String sifre = ed_sifre.getText().toString().trim();

                if (mail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Mail Adresi Girin", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(mail)) {
                    Toast.makeText(getApplicationContext(), "Geçersiz E-mail Adresi!", Toast.LENGTH_SHORT).show();
                } else if (sifre.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Şifre Girin", Toast.LENGTH_SHORT).show();
                } else if(sifre.length() < 6){
                    Toast.makeText(getApplicationContext(), "Şifre 6 karakterden küçük olamaz", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        } );


    }
    private boolean isValidEmail(CharSequence target) {

        //E-MAİL ADRESİ KONTROLÜ
        return (Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}