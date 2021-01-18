package com.login.contact01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbcont extends SQLiteOpenHelper {

    private  static final int VERSION=2;

    private  static final String DB_NAME= "momo";

    private  static final String TABLE= "mouha";
    private  static final String ID= "id";
    private  static final String NOM= "nom";
    private  static final String PRENOM = "prenom";
    private  static final String SEX = "sex";
    private  static final String NUMERO= "num";



    public dbcont( Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLES= "CREATE TABLE  " + TABLE+ " ( "+ ID +  " integer PRIMARY KEY autoincrement," +NOM+ " TEXT," +PRENOM+ " TEXT," +SEX+ " TEXT," +NUMERO+ " TEXT)";
        sqLiteDatabase.execSQL(TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + TABLE;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }



    public void ajoutercont(contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(NOM,contact.getNom());
        values.put(PRENOM,contact.getPrenom());
        values.put(SEX,contact.getSex());
        values.put(NUMERO,contact.getNum());


        db.insert(TABLE,null,values);
        db.close();
    }

    public contact getContact(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursorpos = db.query(
                TABLE,
                new String[]{ID, NOM, PRENOM, SEX, NUMERO},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );
        contact contact;

        if(cursorpos != null){
            cursorpos.moveToFirst();
            contact = new contact(
                    Integer.parseInt(cursorpos.getString(0)),
                    cursorpos.getString(1),
                    cursorpos.getString(2),
                    cursorpos.getString(3),
                    cursorpos.getString(4)
            );
            return  contact;
        }else{
            return null;
        }
    }

    public List<contact> getAllContacts(){
        SQLiteDatabase db = getReadableDatabase();
        List<contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM "  +TABLE;
        Cursor cursorpos = db.rawQuery(query,null);

        if(cursorpos.moveToFirst()){
            do {
                contact contac= new contact();

                contac.setId(Integer.parseInt(cursorpos.getString(0)));
                contac.setNom(cursorpos.getString(1));
                contac.setPrenom(cursorpos.getString(2));
                contac.setSex(cursorpos.getString(3));
                contac.setNum(cursorpos.getString(4));


                contacts.add(contac);
            }while(cursorpos.moveToNext());
        }
        return contacts;
    }


}
