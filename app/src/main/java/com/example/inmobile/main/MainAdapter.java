package com.example.inmobile.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobile.R;
import com.example.inmobile.core.db.entities.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Hassan on 3/27/2019.
 **/
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    List<Data> list = new ArrayList<>();
    Listener listener;


    public static class ViewHolder extends RecyclerView.ViewHolder{


        AppCompatImageView imageView;

        AppCompatTextView textView;

        public ViewHolder(@NonNull View container) {
            super(container);

            imageView = container.findViewById(R.id.imageView);
            textView = container.findViewById(R.id.textView2);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = getItem(position);

        holder.itemView.setOnClickListener((v)->{
            int pos  = holder.getAdapterPosition();
            listener.onClick(getItem(pos),pos);
        });

        //load image
        Picasso.with(holder.itemView.getContext()).load(data.getLink())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.textView.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface Listener{
        public void onClick(Data t,int position);
    }


    public void setOnClickListener(Listener listener){
        this.listener = listener;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }




    public List<Data> getList() {
        return list;
    }

    public Data getItem(int i){
        return list.get(i);
    }
}
