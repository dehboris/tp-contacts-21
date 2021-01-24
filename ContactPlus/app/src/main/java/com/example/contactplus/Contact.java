package com.example.contactplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Contact extends AppCompatActivity {
    private String mName,mNumber;
    private int mImageId;


    public Contact(String name,  String number) {
        this.mName = name;
        this.mNumber = number;

    }
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);




    }
}