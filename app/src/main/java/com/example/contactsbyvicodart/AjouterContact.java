package com.example.contactsbyvicodart;

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

public class AjouterContact extends AppCompatActivity {
    Context context ;
    bdclass bdclass;

    EditText etnom,etprenom,etsexe,etnum,etemail,etfacebook;
    Button ajouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_contact);
        context = this;
        bdclass = new bdclass(context);

        Log.d("count_c","onCreate:" +bdclass.getContactCount());

        etnom = (EditText)findViewById(R.id.nom);
        etprenom = (EditText)findViewById(R.id.prenom);
        etsexe = (EditText)findViewById(R.id.sexe);
        etemail = (EditText)findViewById(R.id.email);
        etnum = (EditText)findViewById(R.id.num);
        etfacebook = (EditText)findViewById(R.id.facebook);

        ajouter = (Button)findViewById(R.id.ajouter);



        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = etnom.getText().toString();
                String prenom = etprenom.getText().toString();
                String sexe = etsexe.getText().toString();
                String num = etnum.getText().toString();
                String email = etemail.getText().toString();
                String facebook = etfacebook.getText().toString();

                if(!TextUtils.isEmpty(nom) || !TextUtils.isEmpty(prenom) || !TextUtils.isEmpty(sexe) ||
                        !TextUtils.isEmpty(num)|| !TextUtils.isEmpty(email)|| !TextUtils.isEmpty(facebook)){
                          Contact contact = new Contact(nom,prenom,sexe,num,email,facebook);
                        bdclass.addContact(contact);
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