package org.o7planning.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoirContact extends Activity{
    Personne details;
    private String contactName,contactPrename,contactSexe,contactFace,contactNum;
    private int contactImage;
    TextView details_name,details_prename,details_sexe,details_face,details_num;
    ImageView details_image;
    Button Retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voircontact);

        details_image=(ImageView) findViewById(R.id.details_image);
        Retour=(Button) findViewById(R.id.Retour);
        details_name=(TextView) findViewById(R.id.details_name);
        details_prename=(TextView) findViewById(R.id.details_prename);
        details_sexe=(TextView) findViewById(R.id.details_sexe);
        details_face= (TextView) findViewById(R.id.details_face);
        details_num=(TextView) findViewById(R.id.details_num);

        Intent intent9=new Intent();
        intent9=getIntent();
        details = (Personne) intent9.getSerializableExtra("details");
        contactImage=details.getImage();
        contactNum=details.getNumero();
        contactName=details.getNom();
        contactPrename=details.getPrenom();
        contactFace=details.getFacebook();
        contactSexe=details.getSexe();


        details_name.setText(contactName);
        details_prename.setText(contactPrename);
        details_sexe.setText(contactSexe);
        details_num.setText(contactNum);
        details_face.setText(contactFace);
        details_image.setImageResource(contactImage);

        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
