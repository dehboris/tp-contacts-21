package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AjoutContactActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_contact);

        nom = findViewById(R.id.editTextPhone1);
        prenom = findViewById(R.id.editTextPhone2);
        numero = findViewById(R.id.editTextPhone3);
    }

    public void add(View v){
        String st1, st2, st3;
        st1 = nom.getText().toString();
        st2 = prenom.getText().toString();
        st3 = numero.getText().toString();

        if(st1.isEmpty() || st2.isEmpty() || st3.isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "Veuillez remplir tout les champs!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            Intent returnIntent = new Intent();

            returnIntent.putExtra("nom",st1);
            returnIntent.putExtra("prenom",st2);
            returnIntent.putExtra("num",st3);

            setResult(Activity.RESULT_OK,returnIntent);
            finish();

        }

    }
}