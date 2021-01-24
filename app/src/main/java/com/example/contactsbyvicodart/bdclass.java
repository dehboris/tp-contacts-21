package com.example.contactsbyvicodart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class bdclass extends SQLiteOpenHelper {

    private  static final int VERSION=2;

    private  static final String DB_NAME= "lescontacts";

    private  static final String CONTACT_TABLE= "mescontacts";
    private  static final String ID= "id";
    private  static final String IMG= "img";
    private  static final String NOM= "nom";
    private  static final String PHENOM = "phenom";
    private  static final String SEX = "sex";
    private  static final String NUM= "num";
    private  static final String EMAIL= "email";
    private  static final String FACEBOOK= "facebook";


    public bdclass( Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           String CREATE_CONTACT_TABLE= "CREATE TABLE  " + CONTACT_TABLE+ " ( "+ ID +  " integer PRIMARY KEY autoincrement," + IMG +  " integer," +NOM+ " TEXT," +PHENOM+ " TEXT," +SEX+ " TEXT," +NUM+ " TEXT," +EMAIL+ " TEXT," +FACEBOOK+ " TEXT)";
           sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql = "DROP TABLE " + CONTACT_TABLE;
        sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
    }



    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(IMG,contact.getImg());
        values.put(NOM,contact.getNom());
        values.put(PHENOM,contact.getPhenom());
        values.put(SEX,contact.getSex());
        values.put(NUM,contact.getNum());
        values.put(EMAIL,contact.getEmail());
        values.put(FACEBOOK,contact.getFacebook());

        db.insert(CONTACT_TABLE,null,values);
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                CONTACT_TABLE,
                new String[]{ID, IMG, NOM, PHENOM, SEX, NUM, EMAIL, FACEBOOK},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );
        Contact contact;

        if(cursor != null){
            cursor.moveToFirst();
            contact = new Contact(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            return  contact;
        }else{
                return null;
        }
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = getReadableDatabase();
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM "  +CONTACT_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();

                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setImg(Integer.parseInt(cursor.getString(1)));
                contact.setNom(cursor.getString(2));
                contact.setPhenom(cursor.getString(3));
                contact.setSex(cursor.getString(4));
                contact.setNum(cursor.getString(5));
                contact.setEmail(cursor.getString(6));
                contact.setFacebook(cursor.getString(7));

                contacts.add(contact);
            }while(cursor.moveToNext());
        }
         return contacts;
    }

    public  int getContactCount(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " +CONTACT_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }


}
