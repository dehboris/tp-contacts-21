package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Contact;
import model.ContactAdapter;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> mContact;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);

        createExampleList();
        if (getArrayList("CONTACT") != null)
            mContact=getArrayList("CONTACT");

        buildRecyclerView();
        fab = findViewById(R.id.activity_main_play_btn);

        //mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        Recuperer();
        saveArrayList(mContact, "CONTACT");


        fab.setOnClickListener(new View.OnClickListener() {
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

    public void saveArrayList(ArrayList<Contact> list, String key){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefs.edit().putString(key,json).apply();
    }

    public ArrayList<Contact> getArrayList(String key){
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void Recuperer()
    {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("nom" ) || intent.hasExtra("prenom") || intent.hasExtra("sexe") || intent.hasExtra("telephone")){ // vérifie qu'une valeur est associée à la clé “edittext”
                  String no = intent.getStringExtra("nom"); // on récupère la valeur associée à la clé
                String preno = intent.getStringExtra("prenom");
                String sex = intent.getStringExtra("sexe");
                String tel = intent.getStringExtra("telephone");
                mContact.add(mContact.size(),new Contact(no, preno, sex, tel, R.drawable.ic_perm_identity_24px));
                mAdapter.notifyItemInserted(mContact.size());
            }

        }

    }

    public void createExampleList()
    {
        mContact= new ArrayList<>();
    }

    public void buildRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ContactAdapter(mContact);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}