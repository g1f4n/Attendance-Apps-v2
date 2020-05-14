package com.juaracoding.attendanceapps.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.attendanceapps.ApiService.APIClient;
import com.juaracoding.attendanceapps.ApiService.APIInterfacesRest;
import com.juaracoding.attendanceapps.R;
import com.juaracoding.attendanceapps.adapter.AdapterFace;
import com.juaracoding.attendanceapps.adapter.AdapterLocation;
import com.juaracoding.attendanceapps.model.siswa.SiswaByNis;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Tracking extends Fragment {
    RecyclerView rvDetailTracking;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_tracking, container, false);

        rvDetailTracking = view.findViewById(R.id.rvDetailTracking);

        String nisSiswa = getActivity().getIntent().getStringExtra("nis_siswa");

        getDetailTracking(nisSiswa);

        return view;
    }

    //  api get siswa
    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void getDetailTracking(String nis){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<SiswaByNis> call3 = apiInterface.getSiswa(nis);
        call3.enqueue(new Callback<SiswaByNis>() {
            @Override
            public void onResponse(Call<SiswaByNis> call, Response<SiswaByNis> response) {
                progressDialog.dismiss();
                SiswaByNis dataSiswa = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataSiswa !=null) {

                    AdapterLocation adapterLocation = new AdapterLocation(dataSiswa.getData(), getActivity());
                    rvDetailTracking.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvDetailTracking.setItemAnimator(new DefaultItemAnimator());
                    rvDetailTracking.setAdapter(adapterLocation);

                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<SiswaByNis> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
