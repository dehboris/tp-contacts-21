package com.example.contactapp;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity<D> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, android.app.LoaderManager.LoaderCallbacks<D> {

    public Adapter mAdapter;
    public static final  int CONTACTLOADER = 0;
    private final Object Contract;

    public MainActivity(Object contract) {
        Contract = contract;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.list);
        mAdapter = new Adapter(this, null);
        listView.setAdapter(mAdapter);

        // whenever we press a listview for updating
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            Uri newUri = ContentUris.withAppendedId(com.example.contactapp.Contract.CONTENT_URI, id);
            intent.setData(newUri);
            startActivity(intent);

        });

        // get the loader running
        getLoaderManager().initLoader(CONTACTLOADER, null, this);
    }

    @Override
    public android.content.Loader<D> onCreateLoader(int id, Bundle args) {

        String[] projection = {Contract.ContactEntry._ID,
                Contract.ContactEntry.COLUMN_NAME,
                Contract.ContactEntry.COLUMN_EMAIL,
                Contract.ContactEntry.COLUMN_PICTURE,
                Contract.ContactEntry.COLUMN_PHONENUMBER,
                com.example.contactapp.Contract.ContactEntry.COLUMN_TYPEOFCONTACT
        };

        return new CursorLoader(this, com.example.contactapp.Contract.ContactEntry.CONTENT_URI,
                projection, null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<D> loader, D data) {
        
    }

    @Override
    public void onLoaderReset(android.content.Loader<D> loader) {

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        
    }


    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        mAdapter.swapCursor(null);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }
}