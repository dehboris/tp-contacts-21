package org.o7planning.contacts;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Images extends Activity{
    private int [] Images={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6,R.drawable.img7

    };
    AdapterImages adapterImages;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        gridView=(GridView)findViewById(R.id.gridview);

        adapterImages=new AdapterImages(Images,this);

        gridView.setAdapter(adapterImages);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Images.this, position + "", Toast.LENGTH_SHORT).show();

                Intent intent3 = new Intent();
                intent3.putExtra("img",Images[position]);
                setResult(1,intent3);
                finish();
            }
        });



    }
}
