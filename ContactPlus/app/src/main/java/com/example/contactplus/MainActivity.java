package com.example.contactplus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button contactAddButton;
    ListView listContacts;

    ListView arrayListContact;
    ContactAdapter contactAdapter;
    Contact contacts;

    final int C_View=1,C_Delete=2;
    private Object ContactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ContactsContract.Contacts> listContact = new ArrayList<ContactsContract.Contacts>();

            arrayListContact= (ListView) findViewById(R.id.listView);

            contactAddButton= (Button) findViewById(R.id.contactAddButton);

            //add button listener
            contactAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, Data.class);
                    startActivityForResult(intent, 1);


                }
            });

           /* ContactAdapter=new ContactAdapter(MainActivity.this, arrayListContact);

        ContactsContract.Contacts set = listContact.set(ContactAdapter);*/

        listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    registerForContextMenu(listContacts);

                }
            });

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);

            if (v.getId() == R.id.listView) {
                menu.add(0, C_View, 1, "View");
                menu.add(0, C_Delete, 2, "Delete");

            }

        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {


            switch (item.getItemId())
            {
                case C_View:

                    Intent intent6=new Intent(MainActivity.this,contact_details.class);
                    AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    int index1 = info1.position;



                    startActivity(intent6);

                    break;

                case C_Delete:
                    Toast.makeText(MainActivity.this,"Delete",Toast.LENGTH_SHORT).show();

                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    int index = info.position;

                    Log.e("index",index+" ");

                    ContactAdapter.notify();

                    break;

            }
            return  true;


        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);


            if (resultCode==2) {

                contacts = (Contact) data.getSerializableExtra("data");

                //arrayListContact.add(contacts);
                ContactAdapter.notify();
            }
            Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0102030405"));
            startActivity(appel);
            Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0102030405"));
            sms.putExtra("sms_body", "Salut les Zéros !");
            startActivity(sms);



    }
}