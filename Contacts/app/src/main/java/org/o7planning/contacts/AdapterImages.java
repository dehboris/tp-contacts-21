package org.o7planning.contacts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
public class AdapterImages extends BaseAdapter{

        private int [] ImageId;
        Context context;

        public AdapterImages(int[] ImageId, Context context) {
            this.ImageId = ImageId;
            this.context = context;
        }

        @Override
        public int getCount() {
            return ImageId.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if(convertView==null)
            {
                imageView=new ImageView(context);
                imageView.setLayoutParams(new ListView.LayoutParams(300,300));
            }
            else
            {
                imageView= (ImageView) convertView;
            }
            imageView.setImageResource(ImageId[position]);
            return imageView;
        }
    }

