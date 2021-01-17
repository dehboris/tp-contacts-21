package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyMovieAdapter  extends RecyclerView.Adapter<MyMovieAdapter.ViewHolder>{
    List<MyMovieData> myMovieData;
  //  MyMovieData[] myMovieData;
    Context context;

    public MyMovieAdapter(List<MyMovieData> myMovieData, MainActivity activity) {
        this.myMovieData = myMovieData;
        this.context = activity;
    }
//    public MyMovieAdapter(MyMovieData[] myMovieData, MainActivity activity) {
//        this.myMovieData = myMovieData;
//        this.context = activity;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //final MyMovieData myMovieDataList = myMovieData[position];
        final MyMovieData myMovieDataList = myMovieData.get(position);
        holder.textViewName.setText(myMovieDataList.getMovieName());
        holder.textViewdate.setText(myMovieDataList.getMoviedate());
        holder.movieImage.setImageResource(myMovieDataList.getMovieImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myMovieDataList.getMovieName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myMovieData.size();
        //return myMovieData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView textViewName;
        TextView textViewdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewdate = itemView.findViewById(R.id.textdate);


        }
    }
}
