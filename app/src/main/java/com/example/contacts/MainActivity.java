package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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
        ctc.setFirstname("POLNAREFF"); ctc.setLastname("Jean Pierre"); ctc.setNumber("96458752");
        contactList.add(ctc);

        ctc = new Contact();
        ctc.setFirstname("HOUESSINON"); ctc.setLastname("Orphé"); ctc.setNumber("96432452");
        contactList.add(ctc);

        ctc = new Contact();
        ctc.setFirstname("MAGENGO"); ctc.setLastname("Gutemberg"); ctc.setNumber("97958752");
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

        infoIntent.putExtra("contactNom",contactList.get(position).getFirstname());
        infoIntent.putExtra("contactPrenom",contactList.get(position).getLastname());
        infoIntent.putExtra("contactNum",contactList.get(position).getNumber());

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

                contact.setFirstname(data.getStringExtra("nom"));
                contact.setLastname(data.getStringExtra("prenom"));
                contact.setNumber(data.getStringExtra("num"));

                contactList.add(contact);

                afficheContact();
            }
        }
    }

}