package com.example.contactsbyvicodart;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
      Context context;
      private  bdclass Bdclass;
      private ListView cl,clo;
      private com.google.android.material.floatingactionbutton.FloatingActionButton ajouter;
      private List<Contact> contacts,sort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Bdclass= new bdclass(context);

        Log.d("count_c","onCreate" +Bdclass.getContactCount());

        cl = findViewById(R.id.clv);

        ajouter = findViewById(R.id.ajoute);

        contacts = new ArrayList<>();
        sort = new ArrayList<>();
        sort = Bdclass.getAllContacts();
        contacts = sorte();
        String[] cont = new String[contacts.size()];

        for(int x=0; x<contacts.size();x++){
            String a =  contacts.get(x).getNom() +"\n"+ contacts.get(x).getPhenom() + "\n" + contacts.get(x).getNum();
            cont[x] = a;

        }
        monadaptateur ae = new monadaptateur(context,R.layout.row,contacts);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,cont);
        cl.setAdapter(ae);

        cl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contacts.get(i);
                Intent intent = new Intent(getApplicationContext(),lecontact.class);
                intent.putExtra("nom",contact.getNom());
                intent.putExtra("prenom",contact.getPhenom());
                intent.putExtra("sexe",contact.getSex());
                intent.putExtra("num",contact.getNum());
                intent.putExtra("email",contact.getEmail());
                intent.putExtra("facebook",contact.getFacebook());
                startActivity(intent);
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AjouterContact.class);
                startActivity(intent);
            }
        });

    }

    public List<Contact> sorte(){
        Collections.sort(sort, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact, Contact t1) {
                return contact.getNom().compareTo(t1.getNom());
            }
        });
        return sort;
    }
}