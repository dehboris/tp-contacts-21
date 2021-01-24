package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import model.Contact;

public class Presentation extends AppCompatActivity {
    private TextView nom;
    private TextView prenom;
    private TextView sexe;
    private TextView tell;
    private FloatingActionButton appel;
    private FloatingActionButton msg;
    private FloatingActionButton editer;
    private FloatingActionButton supprimer;
    private String no =null ;
    private String preno =null;
    private String sex =null;
    private String tel =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation2);

        nom = (TextView) findViewById(R.id.nomp);
        prenom = (TextView) findViewById(R.id.prenomp);
        sexe = (TextView) findViewById(R.id.sexep);
        tell = (TextView) findViewById(R.id.nump);
        appel = findViewById(R.id.appel);
        msg = findViewById(R.id.message);
        editer = findViewById(R.id.editer);
        supprimer = findViewById(R.id.supprimer);

        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("nom" ) || intent.hasExtra("prenom") || intent.hasExtra("sexe") || intent.hasExtra("telephone"))
            { // vérifie qu'une valeur est associée à la clé “edittext”
                no = intent.getStringExtra("nom"); // on récupère la valeur associée à la clé
                preno = intent.getStringExtra("prenom");
                sex = intent.getStringExtra("sexe");
                tel = intent.getStringExtra("telephone");
                nom.setText(no);
                prenom.setText(preno);
                sexe.setText(sex);
                tell.setText(tel);
            }
        }
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tel, null));
                   startActivity(i);
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+tel));
                sms.putExtra("sms_body", "Entrez votre message !");
                startActivity(sms);
            }
        });
        editer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+tel));
                sms.putExtra("sms_body", "Entrez votre message !");
                startActivity(sms);
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+tel));
                sms.putExtra("sms_body", "Entrez votre message !");
                startActivity(sms);
            }
        });
    }
}