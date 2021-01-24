package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    MyRecyclerViewAdapter adapter;
    List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListeContact();

        afficheContact();
    }

    public void initListeContact(){
        Contact ctc = new Contact();
        ctc.setNom("POLNAREFF"); ctc.setPrenom("Jean Pierre"); ctc.setNumero("96458752");
        contactList.add(ctc);

        ctc = new Contact();
        ctc.setNom("HOUESSINON"); ctc.setPrenom("Orphé"); ctc.setNumero("96432452");
        contactList.add(ctc);

        ctc = new Contact();
        ctc.setNom("MAGENGO"); ctc.setPrenom("Gutemberg"); ctc.setNumero("97958752");
        contactList.add(ctc);
    }

    public void afficheContact(){
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new MyRecyclerViewAdapter(this, contactList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent infoIntent = new Intent(this, com.example.contacts.InfoContact.class);

        infoIntent.putExtra("contactNom",contactList.get(position).getNom());
        infoIntent.putExtra("contactPrenom",contactList.get(position).getPrenom());
        infoIntent.putExtra("contactNum",contactList.get(position).getNumero());

        startActivity(infoIntent);
    }

    public void ajoutContact(View v){
        Intent i = new Intent(this, com.example.contacts.AjoutContactActivity.class);
        startActivityForResult(i, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Contact contact = new Contact();

                contact.setNom(data.getStringExtra("nom"));
                contact.setPrenom(data.getStringExtra("prenom"));
                contact.setNumero(data.getStringExtra("num"));

                contactList.add(contact);

                afficheContact();
            }
        }
    }

}