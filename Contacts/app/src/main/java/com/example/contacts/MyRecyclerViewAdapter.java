package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Contact> listeData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // les donnnees sont passées dans le constructeur
    MyRecyclerViewAdapter(Context context, List<Contact> data) {
        this.mInflater = LayoutInflater.from(context);
        this.listeData = data;
    }

    // associer le viewholder au layout row correspond
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_row, parent, false);
        return new ViewHolder(view);
    }

    // lier les  données au textview pour chaque ligne
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = listeData.get(position);
        String text = contact.getPrenom() ;
        holder.myTextView.setText(text);
    }

    // nombre de lignes
    @Override
    public int getItemCount() {
        return listeData.size();
    }


    // stocke et recycle les vues lorsqu'elles défilent hors de l'écran
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.ContactLine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // obtenir des données à la position du clic
    Contact getItem(int id) {
        return listeData.get(id);
    }

    //permet aux événements de clicks d'être capturés
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //
    //l'activité parent implémentera cette méthode pour répondre aux événements de click
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}