package com.projet.contacts;

import androidx.appcompat.app.AppCompatActivity;


import static android.provider.ContactsContract.Intents.*;

public class MainActivity extends AppCompatActivity {
    EditText nom;
    EditText prenom;
    EditText sexe;
    EditText facebook;
    EditText numero;
    Button addContact;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom = findViewById(R.id.etName);
        prenom = findViewById(R.id.etPrenom);
        sexe = findViewById(R.id.etSexe);
        facebook =findViewById(R.id.etFacebook);
        numero = findViewById(R.id.etNumero);
        addContact = findViewById(R.id.btnAjouter);

        addContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!nom.getText().toString().isEmpty() &&  !prenom.getText().toString().isEmpty() && !sexe.getText().toString().isEmpty() &&
                        !facebook.getText().toString().isEmpty() && !numero.getText().toString().isEmpty()){

                    Intent intent=new Intent(Intent.ACTION_INSERT_OR_EDIT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_ITEM_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,nom.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,prenom.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,sexe.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,facebook.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE,numero.getText().toString());
                    if(intent.resolveActivity(getPackageManager()) !=null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "There is no app that support this action",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}