package com.example.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.provider.Contacts.People;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v4.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.provider.ContactsContract.*;

public class Main2Activity extends AppCompatActivity
{
    static final int LIST_CONTACTS_REQUEST_CODE=100;
    static final int ADD_CONTACT_REQUEST_CODE=200;
    static final int DELETE_CONTACT_REQUEST_CODE=300;
    EditText addname,addnum;
    ImageView add,edit,delete;
    ArrayList<Contact> list_ct;
    ListView lv;
    DisplayContactsAdapter adapter;
    Contact current;
    Contact toadd;
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==LIST_CONTACTS_REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Load();
        }
        else if(requestCode==ADD_CONTACT_REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            add.performClick();
        }
        else if(requestCode==DELETE_CONTACT_REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            delete.performClick();
        }
        else
        {
            if (isLanguageFrench())
            {
                Toast.makeText(Main2Activity.this, "Veuillez autoriser l'application", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(Main2Activity.this, "Please grant permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123)
        {
            adapter.setSelectedIndex(-1);
            Load();
            current=null;
        }
        else if(requestCode==240)
        {
            adapter.setSelectedIndex(-1);
            Load();
            current=null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        current=null;
        list_ct=new ArrayList<Contact>();
        add=(ImageView)findViewById(R.id.imageView5);
        edit=(ImageView)findViewById(R.id.imageView6);
        delete=(ImageView)findViewById(R.id.imageView7);
        lv=(ListView)findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter=new DisplayContactsAdapter(Main2Activity.this,R.layout.contacts,list_ct);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                current=(Contact)list_ct.get(position);
                adapter.setSelectedIndex(position);
            }
        });
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (canWriteContacts()) {
                    LayoutInflater li = LayoutInflater.from(Main2Activity.this);
                    View vv = li.inflate(R.layout.prompt, null);
                    addname = (EditText) vv.findViewById(R.id.addname);
                    addnum = (EditText) vv.findViewById(R.id.addnum);
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(Main2Activity.this);
                    alertdialog.setView(vv);
                    if (isLanguageFrench())
                    {
                        TextView t1 = (TextView) vv.findViewById(R.id.textView9);
                        TextView t2 = (TextView) vv.findViewById(R.id.textView10);
                        t1.setText("Nom");
                        t2.setText("Numéro");
                        alertdialog.setPositiveButton("AJOUTER", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (addname.getText().toString().trim().equals("") || addnum.getText().toString().equals("")) {
                                    Toast.makeText(Main2Activity.this, "Veuillez saisir le nom et le numéro", Toast.LENGTH_SHORT).show();
                                } else {
                                    toadd = new Contact(addname.getText().toString(), addnum.getText().toString());
                                    ContentValues values = new ContentValues();
                                    values.put(People.NUMBER, toadd.getPhoneNumber());
                                    values.put(People.TYPE, Phone.TYPE_CUSTOM);
                                    values.put(People.LABEL, toadd.getName());
                                    values.put(People.NAME, toadd.getName());
                                    Uri dataUri = getContentResolver().insert(People.CONTENT_URI, values);
                                    Uri updateUri = Uri.withAppendedPath(dataUri, People.Phones.CONTENT_DIRECTORY);
                                    values.clear();
                                    values.put(People.Phones.TYPE, People.TYPE_MOBILE);
                                    values.put(People.NUMBER, toadd.getPhoneNumber());
                                    updateUri = getContentResolver().insert(updateUri, values);
                                    Load();
                                }
                            }
                        });
                        alertdialog.setNegativeButton("ANNULER", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });
                    } else {
                        alertdialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (addname.getText().toString().trim().equals("") || addnum.getText().toString().equals("")) {
                                    Toast.makeText(Main2Activity.this, "Pleas fill the name and the number", Toast.LENGTH_SHORT).show();
                                } else {
                                    toadd = new Contact(addname.getText().toString(), addnum.getText().toString());
                                    ContentValues values = new ContentValues();
                                    values.put(People.NUMBER, toadd.getPhoneNumber());
                                    values.put(People.TYPE, Phone.TYPE_CUSTOM);
                                    values.put(People.LABEL, toadd.getName());
                                    values.put(People.NAME, toadd.getName());
                                    Uri dataUri = getContentResolver().insert(People.CONTENT_URI, values);
                                    Uri updateUri = Uri.withAppendedPath(dataUri, People.Phones.CONTENT_DIRECTORY);
                                    values.clear();
                                    values.put(People.Phones.TYPE, People.TYPE_MOBILE);
                                    values.put(People.NUMBER, toadd.getPhoneNumber());
                                    updateUri = getContentResolver().insert(updateUri, values);
                                    Load();
                                }
                            }
                        });
                        alertdialog.setNegativeButton("ANNULER", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });
                    }
                    alertdialog.create().show();
                }
                else
                {
                    ActivityCompat.requestPermissions(Main2Activity.this,new String[]{Manifest.permission.WRITE_CONTACTS},ADD_CONTACT_REQUEST_CODE);
                }

            }
        });
        edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(current!=null)
                {
                    Intent in=new Intent(Intent.ACTION_EDIT,Uri.parse("content://contacts/people/"+current.getId()));
                    startActivityForResult(in,123);
                }
                else
                {
                    if(isLanguageFrench())
                    {
                        Toast.makeText(Main2Activity.this,"Veuillez selectionner un contact",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Main2Activity.this,"Please select a contact",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(current!=null)
                {
                    if(canWriteContacts())
                    {
                        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(current.getPhoneNumber()));
                        Cursor cur = getContentResolver().query(contactUri, null, null, null, null);
                        while (cur.moveToNext())
                        {
                            if (cur.getString(cur.getColumnIndex(PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(current.getName()))
                            {
                                String lookupKey = cur.getString(cur.getColumnIndex(Contacts.LOOKUP_KEY));
                                Uri uri = Uri.withAppendedPath(Contacts.CONTENT_LOOKUP_URI, lookupKey);
                                getContentResolver().delete(uri, null, null);
                            }
                        }
                        adapter.setSelectedIndex(-1);
                        Load();
                        current = null;
                    }
                    else
                    {
                        adapter.setSelectedIndex(-1);
                        current=null;
                        ActivityCompat.requestPermissions(Main2Activity.this,new String[]{Manifest.permission.WRITE_CONTACTS},DELETE_CONTACT_REQUEST_CODE);
                    }
                }
                else
                {
                    if(isLanguageFrench())
                    {
                        Toast.makeText(Main2Activity.this,"Veuillez selectionner un contact",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Main2Activity.this,"Please select a contact",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        Translate();
        Load();
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
    public boolean canReadContacts()
    {
        if(ActivityCompat.checkSelfPermission(Main2Activity.this,Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean canWriteContacts()
    {
        if(ActivityCompat.checkSelfPermission(Main2Activity.this,Manifest.permission.WRITE_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void Translate()
    {
        if(isLanguageFrench())
        {
            setTitle("GESTIONNAIRE CONTACTS");
            TextView tv=(TextView) findViewById(R.id.textView);
            tv.setText("LISTE DES CONTACTS");
        }
    }
    public void Load()
    {
        if(canReadContacts())
        {
            list_ct.clear();
            String[] projection = new String[]{Phone.RAW_CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER,};
            Cursor cursor = getContentResolver().query(Phone.CONTENT_URI,projection, null, null, null);
            if (cursor != null)
            {
                while (cursor.moveToNext())
                {
                    Contact ct = new Contact(cursor.getString(cursor.getColumnIndex(Phone.RAW_CONTACT_ID)), cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME)), cursor.getString(cursor.getColumnIndex(Phone.NUMBER)));
                    if(!list_ct.contains(ct))
                    {
                        list_ct.add(ct);
                        adapter.notifyDataSetChanged();
                    }
                }
                if(isLanguageFrench())
                {
                    Toast.makeText(Main2Activity.this,list_ct.size()+" contacts trouvés",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Main2Activity.this,list_ct.size()+" contacts found",Toast.LENGTH_LONG).show();
                }
            }
        }
        else
        {
            ActivityCompat.requestPermissions(Main2Activity.this,new String[]{Manifest.permission.READ_CONTACTS},LIST_CONTACTS_REQUEST_CODE);
        }
    }
}