package com.example.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity
{
    File apppath=null;
    File newfile=null;
    static final int READ_CONTACTS_REQUEST_CODE=100;
    static final int WRITE_CONTACTS_REQUEST_CODE=200;
    static final int WRITE_EXTERNAL_STORAGE=300;
    static int PICK_FILE = 400;
    Gson gson;
    String json="";
    TextView t1,t2,t3;
    ArrayList<Contact> list_cc;
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==READ_CONTACTS_REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Export();
        }
        else if(requestCode==WRITE_CONTACTS_REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            t3.performClick();
        }
        else if(requestCode==WRITE_EXTERNAL_STORAGE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            SaveFile();
        }
        else
        {
            if(isLanguageFrench())
            {
                Toast.makeText(MainActivity.this,"Veuillez autoriser l'application",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Please grant permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            final String fileContent = readTextFile(uri);
            gson=new Gson();
            Thread t=new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        JsonArray array = gson.fromJson(fileContent, JsonArray.class);
                        for (int i = 0; i < array.size(); i++)
                        {
                            JsonObject obj = array.get(i).getAsJsonObject();
                            Contact ct = new Contact(obj.get("name").toString().replace("\"", ""), obj.get("phonenumber").toString().replace("\"", ""));
                            if (!contactExists(ct))
                            {
                                ContentValues values = new ContentValues();
                                values.put(Contacts.People.NUMBER, ct.getPhoneNumber());
                                values.put(Contacts.People.TYPE, Phone.TYPE_CUSTOM);
                                values.put(Contacts.People.LABEL, ct.getName());
                                values.put(Contacts.People.NAME, ct.getName());
                                Uri dataUri = getContentResolver().insert(Contacts.People.CONTENT_URI, values);
                                Uri updateUri = Uri.withAppendedPath(dataUri, Contacts.People.Phones.CONTENT_DIRECTORY);
                                values.clear();
                                values.put(Contacts.People.Phones.TYPE, Contacts.People.TYPE_MOBILE);
                                values.put(Contacts.People.NUMBER, ct.getPhoneNumber());
                                updateUri = getContentResolver().insert(updateUri, values);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        if(isLanguageFrench())
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(MainActivity.this, "Veuillez selectionner un fichier valide", Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
                        }
                        else
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(MainActivity.this, "Please select a valid file", Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
                        }
                    }

                }
            });
            t.start();
            try
            {
                t.join();
            }
            catch (Exception e)
            {
            }
            Intent it=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(it);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson=new Gson();
        list_cc=new ArrayList<Contact>();
        t1=(TextView)findViewById(R.id.textView9);
        t2=(TextView)findViewById(R.id.textView8);
        t3=(TextView)findViewById(R.id.textView10);
        t1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(it);
            }
        });
        t2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String title="";
                String msg="";
                String okbtn="";
                String nobtn="";
                AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
                ad.setCancelable(false);
                if(isLanguageFrench())
                {
                    title="Exportation de vs contacts";
                    msg="Tous vos contacts vont être exportés vers un fichier\n\n";
                    msg+="Ce fichier peut être utilisé lors de l'importation si vous perdez vos contacts \n\n";
                    msg+="Pour procéder à l'exportation, veuillez cliquer sur OK pour créer le fichier sur votre téléphone";
                    okbtn="OK";
                    nobtn="ANNULER";
                }
                else
                {
                    title="Exporting your contacts";
                    msg="All your contacts will be exported to a file\n\n";
                    msg+="This file can be used to import your contacts after if they get deleted\n\n";
                    msg+="To proceed with exporting, please click ok to create the file on your device";
                    okbtn="OKAY";
                    nobtn="CANCEL";
                }
                ad.setTitle(title);
                ad.setMessage(msg);
                ad.setPositiveButton(okbtn, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(canReadContacts())
                        {
                            Export();
                        }
                        else
                        {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACTS_REQUEST_CODE);
                        }
                    }
                });
                ad.setNegativeButton(nobtn, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                ad.create().show();
            }
        });
        t3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(canWriteContacts())
                {
                    Intent intent = new Intent();
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("text/plain");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Open the file"),PICK_FILE);
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_CONTACTS},WRITE_CONTACTS_REQUEST_CODE);
                }
            }
        });
        Translate();
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
    public void Export()
    {
        try
        {
            String[] projection = new String[]{Phone.RAW_CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER,};
            list_cc = new ArrayList<Contact>();
            Cursor cursor = getContentResolver().query(Phone.CONTENT_URI, projection, null, null, null);
            while (cursor.moveToNext())
            {
                Contact ct = new Contact(cursor.getString(cursor.getColumnIndex(Phone.RAW_CONTACT_ID)), cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME)), cursor.getString(cursor.getColumnIndex(Phone.NUMBER)));
                if(!list_cc.contains(ct))
                {
                    list_cc.add(ct);
                }
            }
            json = gson.toJson(list_cc);
            SaveFile();
        }
        catch (Exception ex)
        {
        }
    }
    public void SaveFile()
    {
        try
        {
            if (canWriteExternalStorage())
            {
                apppath = new File(Environment.getExternalStorageDirectory()+"/Download/");
                if(!apppath.exists())
                {
                    apppath.mkdirs();
                }
                newfile=new File(apppath,"contacts.txt");
                FileWriter writer = new FileWriter(newfile);
                writer.append(json);
                writer.flush();
                writer.close();
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                String title = "";
                String msg = "";
                String okbtn = "";
                if (isLanguageFrench())
                {
                    title = "Fichier créé avec succès";
                    msg += "Le fichier a été créé dans Téléchargements/contacts.txt\n\n";
                    msg += "Veuillez conserver ce fichier pour l'utiliser dans l'importation si vous perdez vos contacts";
                    okbtn = "OK";
                }
                else
                {
                    title = "File succesfully created";
                    msg += "The file was created in Downloads/contacts.txt\n\n";
                    msg += "Please save the file in a secure location to use it later for importing if you lose your contacts";
                    okbtn = "OKAY";
                }
                ad.setTitle(title);
                ad.setMessage(msg);
                ad.setPositiveButton(okbtn, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                ad.create().show();
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
            }
        }
        catch (Exception e)
        {
        }
    }
    public String readTextFile(Uri uri)
    {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));
            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                }
            }
        }
        return builder.toString();
    }
    public void Translate()
    {
        if(isLanguageFrench())
        {
            setTitle("GESTIONNAIRE CONTACTS");
            TextView tv1=(TextView)findViewById(R.id.textView2);
            TextView tv2=(TextView)findViewById(R.id.textView9);
            TextView tv3=(TextView)findViewById(R.id.textView8);
            TextView tv4=(TextView)findViewById(R.id.textView10);
            tv1.setText("GESTIONNAIRE DES CONTACTS");
            tv2.setText("GESTION DES CONTACTS");
            tv3.setText("EXPORTATION DES CONTACTS");
            tv4.setText("IMPORTATION DES CONTACTS");
        }
    }
    public boolean contactExists(Contact c)
    {
        String[] projection = new String[]{Phone.RAW_CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER,};
        Cursor cursor = getContentResolver().query(Phone.CONTENT_URI,projection, null, null, null);
        while (cursor.moveToNext())
        {
            Contact ct = new Contact(cursor.getString(cursor.getColumnIndex(Phone.RAW_CONTACT_ID)), cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME)), cursor.getString(cursor.getColumnIndex(Phone.NUMBER)));
            if(c.getName().equals(ct.getName()) && c.getPhoneNumber().equals(ct.getPhoneNumber()))
            {
                return true;
            }
        }
        return false;
    }
    public boolean canReadContacts()
    {
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
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
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean canWriteExternalStorage()
    {
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}