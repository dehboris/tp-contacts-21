package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Enregistrement extends AppCompatActivity implements View.OnClickListener {
    private TextView mEntete;
    private EditText mNameInput1;
    private EditText mNameInput2;
    private EditText mNameInput3;
    private EditText mNameInput4;

    private Button mAnswerButton1;
    private Button mAnswerButton2;
    public static final String BUNDLE_EXTRA_SCORE1 = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_EXTRA_SCORE2 = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_EXTRA_SCORE3 = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_EXTRA_SCORE4 = "BUNDLE_EXTRA_SCORE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);


        mEntete = (TextView) findViewById(R.id.texte);
        mNameInput1 = (EditText) findViewById(R.id.nom);
        mNameInput2 = (EditText) findViewById(R.id.prenom);
        mNameInput3 = (EditText) findViewById(R.id.sexe);
        mNameInput4 = (EditText) findViewById(R.id.tel);
        mAnswerButton2 = (Button) findViewById(R.id.btnEnregistrer);
        mAnswerButton1 = (Button) findViewById(R.id.btnAnnuler);
        mAnswerButton2.setEnabled(false);
        mNameInput1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAnswerButton2.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Maintenant, ce serait quand même bien de distinguer le bouton sur lequel le joueur a appuyé. Pour ce faire, nous allons
        //utiliser la méthode setTag(), et assigner un identifiant différent pour chaque bouton
        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);

        // Use the same listener for the four buttons.
        // The tag value will be used to distinguish the button triggered
        // La méthode onClick() sera désormais appelée à chaque fois que l'utilisateur cliquera sur un des quatre boutons de réponse.
        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if (responseIndex==0)
            finish();
        else
        {
             String nom = mNameInput1.getText().toString();
             String prenom = mNameInput2.getText().toString();
             String sexe = mNameInput3.getText().toString();
             String telephone = mNameInput4.getText().toString();
             Toast.makeText(this, "Enregistrement réussie", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(Enregistrement.this, MainActivity.class);
            intent1.putExtra("nom", nom);
            intent1.putExtra("prenom", prenom);
            intent1.putExtra("sexe", sexe);
            intent1.putExtra("telephone", telephone);
            // démarrer une autre activité et recevoir un résultat
            //startActivityForResult(enregistrementIntent,ENREGISTREMENT_REQUEST_CODE);
            startActivity(intent1);
        }
    }
}