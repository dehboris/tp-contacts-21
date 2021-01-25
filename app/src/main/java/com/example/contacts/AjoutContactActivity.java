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

    private EditText firstname;
    private EditText lastname;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_contact);

        firstname = findViewById(R.id.editTextPhone1);
        lastname = findViewById(R.id.editTextPhone2);
        number = findViewById(R.id.editTextPhone3);
    }

    public void add(View v){
        String st1, st2, st3;
        st1 = firstname.getText().toString();
        st2 = lastname.getText().toString();
        st3 = number.getText().toString();

        Intent returnIntent = new Intent();

        returnIntent.putExtra("nom",st1);
        returnIntent.putExtra("prenom",st2);
        returnIntent.putExtra("num",st3);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }
}