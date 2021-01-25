package com.login.appcontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class bdrcontact extends SQLiteOpenHelper {

    private  static final int VERSION=2;

    private  static final String DB_NAME= "Hughes";

    private  static final String TABLE= "cont";
    private  static final String ID= "id";
    private  static final String NOM= "nom";
    private  static final String PRENOM = "prenom";
    private  static final String SEX = "sex";
    private  static final String NUM= "num";



    public bdrcontact( Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CONTACT_TABLE= "CREATE TABLE  " + TABLE+ " ( "+ ID +  " integer PRIMARY KEY autoincrement," +NOM+ " TEXT," +PRENOM+ " TEXT," +SEX+ " TEXT," +NUM+ " TEXT)";
        sqLiteDatabase.execSQL(CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + TABLE;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }



    public void addC(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(NOM,contact.getNom());
        values.put(PRENOM,contact.getPrenom());
        values.put(SEX,contact.getSex());
        values.put(NUM,contact.getNum());


        db.insert(TABLE,null,values);
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
              TABLE,
                new String[]{ID, NOM, PRENOM, SEX, NUM},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );
        Contact contact;

        if(cursor != null){
            cursor.moveToFirst();
            contact = new Contact(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)

            );
            return  contact;
        }else{
            return null;
        }
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = getReadableDatabase();
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM "  +TABLE;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();

                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setNom(cursor.getString(1));
                contact.setPrenom(cursor.getString(2));
                contact.setSex(cursor.getString(3));
                contact.setNum(cursor.getString(4));

                contacts.add(contact);
            }while(cursor.moveToNext());
        }
        return contacts;
    }


}
