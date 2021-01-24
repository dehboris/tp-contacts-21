package model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.Presentation;
import com.example.contact.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> mcontactList;

    Context context;

    public ContactAdapter(ArrayList<Contact> contacts)
    {
        this.mcontactList = contacts;
    }

//    onCreateViewHolder()  : Elle nous permet de créer un ViewHolder à partir du
//    layout XML représentant chaque ligne de la RecyclerView. Celle-ci sera appelée
//    pour les premières lignes visibles de la RecyclerView.
//    Pourquoi pas les autres ? Tout simplement car la RecyclerView possède un système
//    permettant de réutiliser (ou recycler... ;) ) les ViewHolder déjà créés. Il faut savoir
//    que la création d'une vue sur Android est une action qui demande beaucoup de ressources.
//    Imaginez donc avoir 1000 lignes à afficher dans votre application : sans ce mécanisme de
//        réutilisation, cette dernière souffrirait de ralentissements assez importants.
    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        ContactAdapter.ViewHolder evh = new ContactAdapter.ViewHolder(v);
        return evh;
    }


    //    onBindViewHolder()  : Cette méthode est appelée pour chacune des lignes visibles affichées dans
//    notre RecyclerView.  C'est généralement ici que l'on met à jour leur apparence. Dans notre cas
//    nous appelons la méthode du ViewHolder que nous avons précédemment créée, afin de mettre à jour
//    son TextView à partir d'un contact. D'ailleurs, nous avons grâce à la variable position, récupéré
//    l'objet contact correspondant dans notre liste d'objet.
    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        final Contact contact = mcontactList.get(position);
        holder.prenom.setText(contact.getPrenom());
        holder.telephone.setText(contact.getTel());
        holder.profil.setImageResource(contact.getProfil());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent present = new Intent(view.getContext(), Presentation.class);
                present.putExtra("nom", contact.getNom());
                present.putExtra("prenom", contact.getPrenom());
                present.putExtra("sexe", contact.getSexe());
                present.putExtra("telephone", contact.getTel());
                view.getContext().startActivity(present);
                //Toast.makeText(view.getContext(), contact.getPrenom(), Toast.LENGTH_SHORT).show();
            }
        });

    }


//    liste d'objet, et ainsi indiquer à l'Adapter le nombre de lignes que peut contenir la RecyclerView.
    @Override
    public int getItemCount()
    {
        return mcontactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profil;
        TextView prenom;
        TextView telephone;

        public ViewHolder(View view) {
            super(view);
            profil = view.findViewById(R.id.profil);
            prenom = view.findViewById(R.id.prenom);
            telephone = view.findViewById(R.id.telephone);
        }
    }
}
