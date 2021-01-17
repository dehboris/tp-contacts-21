package org.o7planning.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DonneesRecuperees extends Activity{
    EditText editname,editprename,editsexe,editface,editnum;
    ImageView contactImage;
    Button Enregistrer;
    private int GetImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donnee);

        editname=(EditText) findViewById(R.id.editname);
        editprename=(EditText) findViewById(R.id.editprename);
        editsexe=(EditText) findViewById(R.id.editsexe);
        editface=(EditText) findViewById(R.id.editface);
        editnum=(EditText) findViewById(R.id.editnum);
        contactImage=(ImageView) findViewById(R.id.contactImage);
        Enregistrer=(Button) findViewById(R.id.Enregistrer);

        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                  Intent intent2=new Intent(DonneesRecuperees.this,Images.class);
                  startActivityForResult(intent2,1);
            }
        });

        Enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personne personne=new Personne(editname.getText().toString(),editprename.getText().toString(),
                        editsexe.getText().toString(),editface.getText().toString(),editnum.getText().toString(),GetImg);

                Intent intent5=new Intent(DonneesRecuperees.this,MainActivity.class);
                intent5.putExtra("data",personne);
                setResult(2,intent5);

                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        GetImg=data.getExtras().getInt("img",1);
        contactImage.setImageResource(GetImg);
    }


}
