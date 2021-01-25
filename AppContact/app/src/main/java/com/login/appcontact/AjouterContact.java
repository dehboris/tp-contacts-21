package com.login.appcontact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AjouterContact extends AppCompatActivity {
    Context context ;
    bdrcontact Bdrcontact;

    EditText nom,prenom,sexe,num;
    Button ajouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_contact);
        context = this;
        Bdrcontact = new bdrcontact(context);


        nom = (EditText)findViewById(R.id.itnom);
        prenom = (EditText)findViewById(R.id.itprenom);
        sexe = (EditText)findViewById(R.id.itsexe);
        num = (EditText)findViewById(R.id.itnum);


        ajouter = (Button)findViewById(R.id.ajouterbtn);



        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nom = nom.getText().toString();
                String Prenom = prenom.getText().toString();
                String Sexe = sexe.getText().toString();
                String Num = num.getText().toString();


                if(!TextUtils.isEmpty(Nom) || !TextUtils.isEmpty(Prenom) || !TextUtils.isEmpty(Sexe) ||
                        !TextUtils.isEmpty(Num)){
                    Contact contact = new Contact(Nom,Prenom,Sexe,Num);
                    Bdrcontact.addC(contact);
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