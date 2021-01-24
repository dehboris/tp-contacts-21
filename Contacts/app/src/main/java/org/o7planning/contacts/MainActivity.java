package org.o7planning.contacts;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends Activity {

    Button Ajouter;
    ListView listContacts;

    ArrayList<Personne> arrayListContact;
    PersonneAdapter personneAdapter;
    Personne personne;

    final int C_View=1,C_Delete=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayListContact=new ArrayList<Personne>();
        listContacts= (ListView) findViewById(R.id.listView);
        Ajouter= (Button) findViewById(R.id.Ajouter);
        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DonneesRecuperees.class);
                startActivityForResult(intent, 1);

            }
        });

        personneAdapter=new PersonneAdapter(MainActivity.this,arrayListContact);

        listContacts.setAdapter(personneAdapter);

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
            menu.add(0, C_View, 1,"View");
            menu.add(0, C_Delete, 2, "Delete");

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case C_View:

                Intent intent6=new Intent(MainActivity.this,VoirContact.class);
                AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index1 = info1.position;

                intent6.putExtra("details", arrayListContact.get(index1));

                startActivity(intent6);

                break;

            case C_Delete:
                Toast.makeText(MainActivity.this,"Delete",Toast.LENGTH_SHORT).show();

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index = info.position;

                Log.e("index",index+" ");
                arrayListContact.remove(index);
                personneAdapter.notifyDataSetChanged();

                break;

        }
        return  true;


    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);


            if (resultCode==2) {

                personne = (Personne) data.getSerializableExtra("data");

                arrayListContact.add(personne);
                personneAdapter.notifyDataSetChanged();
            }


        }

    }


