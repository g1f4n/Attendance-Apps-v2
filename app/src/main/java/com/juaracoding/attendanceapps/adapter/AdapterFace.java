package com.juaracoding.attendanceapps.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.attendanceapps.R;
import com.juaracoding.attendanceapps.model.siswa.Datum;
import com.juaracoding.attendanceapps.model.siswa.SiswaByNis;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFace extends RecyclerView.Adapter<AdapterFace.ViewHolder> {

    List<Datum> data;
    Context context;

    public AdapterFace(List<Datum> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFace.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_face, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFace.ViewHolder holder, int position) {
        String img = "http://192.168.43.38:5000/download/" + data.get(position).getDataWajah();
        Picasso.get().load(img).into(holder.imgFaces);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgFaces;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFaces = (ImageView) itemView.findViewById(R.id.imgFaces);
        }
    }
}
