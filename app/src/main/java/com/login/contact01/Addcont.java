package com.login.contact01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Addcont extends AppCompatActivity {
    Context context ;
    dbcont dbcontt;

    EditText nom,prenom,sexe,num;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcont);
        context = this;
       dbcontt = new dbcont(context);



        nom = (EditText)findViewById(R.id.ednom);
        prenom = (EditText)findViewById(R.id.edprenom);
        sexe = (EditText)findViewById(R.id.edsexe);
        num = (EditText)findViewById(R.id.ednum);


        add = (Button)findViewById(R.id.btnadd);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nom = nom.getText().toString();
                String Prenom = prenom.getText().toString();
                String Sexe = sexe.getText().toString();
                String Num = num.getText().toString();


                if(!TextUtils.isEmpty(Nom) || !TextUtils.isEmpty(Prenom) || !TextUtils.isEmpty(Sexe) ||
                        !TextUtils.isEmpty(Num)){
                    contact contact = new contact(Nom,Prenom,Sexe,Num);
                    dbcontt.ajoutercont(contact);
                    startActivity(new Intent(context,MainActivity.class));
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Il y a des champs vides remplissez les")
                            .setNegativeButton("OK",null)
                            .show();
                }

            }
        });
    }
}