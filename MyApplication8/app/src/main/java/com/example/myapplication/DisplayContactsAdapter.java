package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class DisplayContactsAdapter extends ArrayAdapter<Contact>
{
    Context con;
    int res;
    ArrayList<Contact> list_ct;
    static int selectedIndex;
    int color=Color.rgb(201, 235, 237);
    public void setSelectedIndex(int ind)
    {
        selectedIndex = ind;
        notifyDataSetChanged();
    }

    public DisplayContactsAdapter(Context context,int resource,ArrayList<Contact> objects)
    {
        super(context, resource, objects);
        con = context;
        res = resource;
        list_ct = objects;
        selectedIndex=-1;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater infa=((Activity) con).getLayoutInflater();
        final Contact ct=(Contact) list_ct.get(position);
        View v=infa.inflate(res,parent,false);
        TextView tv_name=(TextView)v.findViewById(R.id.textView6);
        TextView tv_num=(TextView)v.findViewById(R.id.textView3);
        TextView val_name=(TextView)v.findViewById(R.id.val_name);
        TextView val_num=(TextView)v.findViewById(R.id.val_num);
        val_name.setText(""+ct.getName());
        val_num.setText(""+ct.getPhoneNumber());
        if(selectedIndex!= -1 && position == selectedIndex)
        {
            v.setBackgroundColor(Color.YELLOW);
        }
        else
        {
            v.setBackgroundColor(color);
        }
        if(isLanguageFrench())
        {
            tv_name.setText("Nom");
            tv_num.setText("Numéro");
        }
        return  v;
    }
    public boolean isLanguageFrench()
    {
        String[] french_locales=new String[]{"fr","fr_CA","fr_FR","fr_LU","fr_CH"};
        for(String locale:french_locales)
        {
            if(locale.equals(Resources.getSystem().getConfiguration().locale.getLanguage()))
            {
                return true;
            }
        }
        return false;
    }
}