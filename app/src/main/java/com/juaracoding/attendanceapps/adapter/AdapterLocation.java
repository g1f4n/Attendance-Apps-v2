package com.juaracoding.attendanceapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.attendanceapps.R;
import com.juaracoding.attendanceapps.model.siswa.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.ViewHolder> {

    List<Datum> data;
    Context context;

    public AdapterLocation(List<Datum> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterLocation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_tracking2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocation.ViewHolder holder, int position) {
        holder.txtNamaDetail.setText(data.get(position).getNamaSiswa());
        holder.txtJamMasukDetail.setText(data.get(position).getJamMasuk());
        holder.txtJamKeluarDetail.setText(data.get(position).getJamKeluar());

        String imageLokasi = "http://192.168.43.38:5000/download/" + data.get(position).getLokasiTerakhir();
        Picasso.get().load(imageLokasi).into(holder.imgLokasi);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgLokasi;
        public TextView txtNamaDetail, txtJamMasukDetail, txtJamKeluarDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLokasi = (ImageView) itemView.findViewById(R.id.imgLokasi);
            txtNamaDetail = (TextView) itemView.findViewById(R.id.txtNamaDetail);
            txtJamMasukDetail = (TextView) itemView.findViewById(R.id.txtJamMasukDetail);
            txtJamKeluarDetail = (TextView) itemView.findViewById(R.id.txtJamKeluarDetail);
        }
    }
}
