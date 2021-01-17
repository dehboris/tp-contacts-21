package com.example.contact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Contact;

public class MainActivity extends AppCompatActivity {
    private Button mPlayButton;
    private List<MyMovieData> persone =  new ArrayList<>();
    private MyMovieData myMovieData;
    private SharedPreferences mPreferences;
    public static final int ENREGISTREMENT_REQUEST_CODE = 42;
    String no = "";
    String preno= "";
    String sex  ="";
    String tel ="";

//    MyMovieData [] myMovieData = new MyMovieData[]{
//            new MyMovieData("Prince Le Bon", "62907841", R.drawable.ic_launcher_background, "HOUNDJO", "Masculin"),
//            new MyMovieData("Jefferson", "97283134", R.drawable.ic_launcher_background,"HOUNDJO", "Masculin"),
//            new MyMovieData("Théophile", "97009123", R.drawable.ic_launcher_background, "HOUNDJO", "Masculin"),
//            new MyMovieData("Rosine", "95407188", R.drawable.ic_launcher_background, "HOUNDJO", "Masculin"),
//            new MyMovieData("Peace Love", "90071214", R.drawable.ic_launcher_background, "HOUNDJO", "Masculin"),
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myMovieData = new MyMovieData("Jeff","62907841",R.drawable.ic_launcher_background,"HOUNDJO","");
        persone.add(myMovieData);
        persone.add(new MyMovieData("Rosine", "95407188", R.drawable.ic_launcher_background, "HOUNDJO", "Masculin"));
        persone.add(new MyMovieData("n", "55", R.drawable.ic_launcher_background, "HOUNDJO", "Masculin"));
        MyMovieAdapter myMovieAdapter = new MyMovieAdapter(persone, MainActivity.this);
        recyclerView.setAdapter(myMovieAdapter);
        presenter();
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quand l'utilisateur clique sur le bouton
                //Je vous l'accorde, cela peut sembler bien compliqué pour démarrer une simple activité.
                // Mais dans les faits, cela se traduit simplement par les deux lignes suivantes :
                Intent enregistrementIntent = new Intent(MainActivity.this, Enregistrement.class);
                // démarrer une autre activité et recevoir un résultat
                //startActivityForResult(enregistrementIntent,ENREGISTREMENT_REQUEST_CODE);
                startActivity(enregistrementIntent);
            }
        });
    }

    private void presenter()
    {
        List<MyMovieData> p =  new ArrayList<>();
        MyMovieData hhh = new MyMovieData("","",R.drawable.ic_launcher_background,"","");
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("nom")){ // vérifie qu'une valeur est associée à la clé “edittext”
                no = intent.getStringExtra("nom"); // on récupère la valeur associée à la clé
            }
        }
        if (intent != null){
            if (intent.hasExtra("prenom")){ // vérifie qu'une valeur est associée à la clé “edittext”
                preno = intent.getStringExtra("prenom"); // on récupère la valeur associée à la clé
            }
        }
        if (intent != null){
            if (intent.hasExtra("sexe")){ // vérifie qu'une valeur est associée à la clé “edittext”
                sex = intent.getStringExtra("sexe"); // on récupère la valeur associée à la clé
            }
        }
        if (intent != null){
            if (intent.hasExtra("telephone")){ // vérifie qu'une valeur est associée à la clé “edittext”
                tel = intent.getStringExtra("telephone"); // on récupère la valeur associée à la clé
            }
        }

        RecyclerView recyclerView1 = findViewById(R.id.recycleview);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        hhh.setMovieName(preno);
        hhh.setMoviedate(tel);
        hhh.setMovieImage(R.drawable.ic_launcher_background);
        hhh.setNom(no);
        hhh.setSexe(sex);
        p.add(hhh);
        persone.addAll(p);

//        myMovieData = new MyMovieData[]{
//                new MyMovieData(preno, tel, R.drawable.ic_launcher_background, no, sex)
//        };
//        MyMovieData [] myMovieData2 = new MyMovieData[myMovieData.length];
//        for (int i = 0; i < myMovieData.length; i++) {
//            myMovieData2[i] = myMovieData[i];
//        }
//        myMovieData = null;
//myMovieData2 = concat(myMovieData,myMovieData1);

        MyMovieAdapter myMovieAdapter = new MyMovieAdapter(persone, MainActivity.this);
        recyclerView1.setAdapter(myMovieAdapter);

        }
//    public static <Type> Type[] concat(Type[] premierTableau, Type[] secondTableau) {
//        Type[] nouveauTableau = Arrays.copyOf(premierTableau, premierTableau.length + secondTableau.length);
//        System.arraycopy(secondTableau, 0, nouveauTableau, premierTableau.length, secondTableau.length);
//        return nouveauTableau;
//    }

}