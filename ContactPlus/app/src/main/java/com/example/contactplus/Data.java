package com.example.contactplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Data extends AppCompatActivity {

    EditText editName,editNumber;
    Button save;
    private Object Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        editName= (EditText) findViewById(R.id.editName);
        editNumber= (EditText) findViewById(R.id.editNumber);



        save= (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact contacts=new Contact(editName.getText().toString(),editNumber.getText().toString()
                        );

                Intent intent5=new Intent(Data.this,MainActivity.class);

                intent5.putExtra("data", (Bundle) Contact);
                setResult(2, intent5);

                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    }
