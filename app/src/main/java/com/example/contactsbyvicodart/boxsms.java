package com.example.contactsbyvicodart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class boxsms extends AppCompatActivity {

    private EditText  numero,message;
    private Button  envoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxsms);

        ActivityCompat.requestPermissions(boxsms.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        numero=findViewById(R.id.numoiu);
        message= findViewById(R.id.meg);
        envoyer=findViewById(R.id.Envoyer);

        Intent intent=getIntent();
        String numeroi = intent.getStringExtra("phone");
        numero.setText(numeroi);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(boxsms.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(boxsms.this,"Permission refuse",Toast.LENGTH_SHORT).show();
                }else{
                    String mesaages =  message.getText().toString();
                    String numeros =  numero.getText().toString();
              if(mesaages.isEmpty()){
                  Toast.makeText(boxsms.this,"Votre message est vide",Toast.LENGTH_SHORT).show();
              }else {
                  SmsManager monmessage = SmsManager.getDefault();
                  monmessage.sendTextMessage(numeros,null,mesaages,null,null);
                  Toast.makeText(boxsms.this,"Message envoye",Toast.LENGTH_SHORT).show();
              }

                }

            }
        });

    }
}