package com.example.contactsbyvicodart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class monadaptateur extends ArrayAdapter<Contact> {
    private  Context mc;
    private int mr;


    public monadaptateur(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);

        this.mc=context;
        this.mr=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater l =LayoutInflater.from(mc);

        convertView = l.inflate(mr,parent,false);

        ImageView i = convertView.findViewById(R.id.imag);

        TextView tnom = convertView.findViewById(R.id.tex);

        TextView tnu = convertView.findViewById(R.id.nu);

        i.setImageResource(getItem(position).getImg());

        tnom.setText(getItem(position).getNom()+"  "+getItem(position).getPhenom());

        tnu.setText(getItem(position).getNum());

        return convertView;
    }
}
