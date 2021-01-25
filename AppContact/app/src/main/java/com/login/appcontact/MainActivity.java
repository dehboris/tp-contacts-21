package com.login.appcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context;
    private  bdrcontact Bdrcontact;
    private ListView  msg;
    private ImageButton Add;
    private List<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Bdrcontact= new bdrcontact(context);


        msg = findViewById(R.id.gl);
        Add = findViewById(R.id.ajoutbtn);

        contacts = new ArrayList<>();
        contacts = Bdrcontact.getAllContacts();

        String[] cont = new String[contacts.size()];

        for(int x=0; x<contacts.size();x++){
            String a =  contacts.get(x).getNom() +"\n"+ contacts.get(x).getPrenom() + "\n" + contacts.get(x).getNum();
            cont[x] = a;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,cont);
        msg.setAdapter(adapter);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AjouterContact.class);
                startActivity(intent);
            }
        });

    }
}