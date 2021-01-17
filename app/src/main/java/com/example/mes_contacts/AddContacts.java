package com.example.mes_contacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

    public class AddContacts extends AppCompatActivity {

        Context context;
        DBHandler dbHandler;

        EditText et_name,et_number,et_email,et_organization;
        Button relationship,add;

        String relationshipString;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_conctacts);

            context = this;
            dbHandler = new DBHandler(context);

            et_name = (EditText)findViewById(R.id.name);
            et_number = (EditText)findViewById(R.id.number);
            et_email = (EditText)findViewById(R.id.email);
            et_organization = (EditText)findViewById(R.id.org);

            relationship = (Button)findViewById(R.id.relationship);
            add = (Button)findViewById(R.id.add);

            relationshipString = "Unspecified";

            relationship.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence options[] = {"Business","Friend","Acquaintance","Other"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Choose Relationship Type")
                            .setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(i == 0){
                                        relationshipString = "Business";
                                    } else if(i == 1){
                                        relationshipString = "Friend";
                                    } else if(i == 2){
                                        relationshipString = "Acquaintance";
                                    } else if(i == 3){
                                        relationshipString = "Other";
                                    }
                                }
                            })
                            .show();
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = et_name.getText().toString();
                    String number = et_number.getText().toString();
                    String email = et_email.getText().toString();
                    String organization = et_organization.getText().toString();

                    if(name.trim().length() > 0 && number.trim().length() > 0 && email.trim().length() > 0 && organization.trim().length() > 0){
                        Contacts contact = new Contacts(name,number,email,organization,relationshipString);
                        dbHandler.addContact(contact);
                        startActivity(new Intent(context,MainActivity.class));
                    } else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Please fill all the fields")
                                .setNegativeButton("OK",null)
                                .show();
                    }
                }
            });
        }
    }
