package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoContact extends AppCompatActivity {

    String name;
    String firstname;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contact);

        TextView tv1 = findViewById(R.id.nameTextView);
        TextView tv2 = findViewById(R.id.firstnameTextView);
        TextView tv3 = findViewById(R.id.numberTextView);

        Intent sourceActivityIntent = getIntent();

        name = sourceActivityIntent.getStringExtra("contactNom");
        firstname = sourceActivityIntent.getStringExtra("contactPrenom");
        number = sourceActivityIntent.getStringExtra("contactNum");

        tv1.setText(name);
        tv2.setText(firstname);
        tv3.setText(number);
    }

    public void appel(View v){
        Intent appel = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ number));
        startActivity(appel);
    }

    public void message(View v){
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+number));
        sms.putExtra("sms_body", "");
        startActivity(sms);
    }
}