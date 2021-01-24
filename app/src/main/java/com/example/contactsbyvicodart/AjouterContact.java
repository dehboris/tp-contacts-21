package com.example.contactsbyvicodart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class AjouterContact extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Context context ;
    bdclass bdclass;
    Spinner etsexe;
    EditText etnom,etimg,etprenom,etnum,etemail,etfacebook;
    Button ajouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_contact);



        context = this;
        bdclass = new bdclass(context);

        Log.d("count_c","onCreate:" +bdclass.getContactCount());
        etimg=  (EditText)findViewById(R.id.img);
        etnom = (EditText)findViewById(R.id.nom);
        etprenom = (EditText)findViewById(R.id.prenom);
        etsexe = (Spinner) findViewById(R.id.sexes);
        etemail = (EditText)findViewById(R.id.email);
        etnum = (EditText)findViewById(R.id.num);
        etfacebook = (EditText)findViewById(R.id.facebook);

        ajouter = (Button)findViewById(R.id.ajouter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sexesm, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        etsexe.setAdapter(adapter);
        etsexe.setOnItemSelectedListener(this);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = R.drawable.ftgtgtt;
                etimg.setText(String.valueOf(c));

                Integer img = Integer.parseInt(etimg.getText().toString());
                String nom = etnom.getText().toString();
                String prenom = etprenom.getText().toString();
                String sexe = etsexe.getSelectedItem().toString();
                String num = etnum.getText().toString();
                String email = etemail.getText().toString();
                String facebook = etfacebook.getText().toString();

                if(!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(prenom) && !TextUtils.isEmpty(sexe) &&
                        !TextUtils.isEmpty(num)&& !TextUtils.isEmpty(email)&& !TextUtils.isEmpty(facebook)){
                          Contact contact = new Contact(img,nom,prenom,sexe,num,email,facebook);
                        bdclass.addContact(contact);
                        startActivity(new Intent(context,MainActivity.class));
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Certains cases sont vides")
                            .setNegativeButton("OK",null)
                            .show();
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}