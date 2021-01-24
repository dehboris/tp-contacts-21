package com.example.contactsbyvicodart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class lecontact extends AppCompatActivity {

    private TextView n,em,nu,f;
    private FloatingActionButton mes,appe;
    private static final int request_call =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecontact);

        n= findViewById(R.id.npoi);
        nu= findViewById(R.id.numoi);
        em= findViewById(R.id.emoi);
        f= findViewById(R.id.facoi);

        mes= findViewById(R.id.sms);
        appe= findViewById(R.id.phone);

        Intent intent=getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String num = intent.getStringExtra("num");
        String email = intent.getStringExtra("email");
        String facebook = intent.getStringExtra("facebook");

        n.setText(nom+""+prenom);
        nu.setText(num);
        em.setText(email);
        f.setText(facebook);



        appe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     fonctionappel();
            }
        });

        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(),boxsms.class);
                    intent.putExtra("phone",num);
                    startActivity(intent);

            }
        });
    }

    private void fonctionappel(){
         String numero = nu.getText().toString();
         if(numero.trim().length()>0){
                   if(ContextCompat.checkSelfPermission(lecontact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                       ActivityCompat.requestPermissions(lecontact.this,new String[]{Manifest.permission.CALL_PHONE},request_call);
                   }else{
                       String conf = "tel:" + numero;
                       startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(conf)));
                   }
         }else{
             Toast.makeText(lecontact.this,"Entrez un numero correct",Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == request_call){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                fonctionappel();
            }else{
                Toast.makeText(this,"Permission refuse",Toast.LENGTH_SHORT).show();
            }
        }
    }
}