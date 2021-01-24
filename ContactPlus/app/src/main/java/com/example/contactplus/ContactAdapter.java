package com.example.contactplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private final ArrayList<Contact> contact;
    Context context;



    public ContactAdapter(Context context, ArrayList<Contact> contact) {
        this.context = context;
        this.contact = contact;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_adapter);
    }*/

    @Override
    public int getCount() {

            return contact.size();
        }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if(convertView==null)
        {
            LayoutInflater layoutInflater= LayoutInflater.from(context);
           /* view = layoutInflater.inflate(null,
                    R.layout.contact.this);*/
        }

        else
        {
            view=convertView;
        }


        TextView contactName= (TextView) view.findViewById(R.id.contactName);

        //get data


        /*contact =contact.get(position);

        contactName.setText(((Contact) contact).getNumber());
        System.getProperty("line.separator");
        Log.e("name", ((Contact) contact).getName() + " ");*/

        return view;

    }
}