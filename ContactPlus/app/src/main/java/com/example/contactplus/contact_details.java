package com.example.contactplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class contact_details extends AppCompatActivity {

    ContactsContract.Contacts ContactDetails;
    private String mContactName,mContactNumber;

    TextView details_name,details_number;

    Button backButton;
    private Object contact_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        backButton= (Button) findViewById(R.id.backButton);

        details_name= (TextView) findViewById(R.id.details_name);
        details_number= (TextView) findViewById(R.id.details_number);

        Intent intent9=new Intent();
        intent9=getIntent();
        contact_details = (Contact) intent9.getSerializableExtra("details");
        mContactNumber=ContactDetails.toString();
        mContactName=ContactDetails.toString();



        details_name.setText(mContactName);



        details_number.setText(mContactNumber);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    }
