package com.apokeskin.breakingbad;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<RecyclerModel> arrayList;
    SimpleDraweeView draweeView;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,nickName;
        View image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.characterName);
            nickName=itemView.findViewById(R.id.characterNickName);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view);

        }
    }
    public RecyclerAdapter(ArrayList<RecyclerModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerModel recyclerModel=arrayList.get(position);
        holder.name.setText(recyclerModel.getCharacterName());
        holder.nickName.setText(recyclerModel.getCharacterNickName());
        //holder.image.setI
        Uri uri = Uri.parse(recyclerModel.getImage());
        draweeView.setImageURI(uri);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
