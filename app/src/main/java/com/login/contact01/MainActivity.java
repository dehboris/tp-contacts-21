package com.login.contact01;

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
    private  dbcont Dbcont;
    private ListView ms;
    private ImageButton add;
    private List<contact> contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Dbcont= new dbcont(context);



        ms = findViewById(R.id.mcs);
        add = findViewById(R.id.ajouterbtn);

        contact = new ArrayList<>();
        contact = Dbcont.getAllContacts();

        String[] cont = new String[contact.size()];

        for(int x=0; x<contact.size();x++){
            String a =  contact.get(x).getNom() +"\n"+ contact.get(x).getPrenom() + "\n" + contact.get(x).getNum();
            cont[x] = a;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,cont);
        ms.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Addcont.class);
                startActivity(intent);
            }
        });

    }
}