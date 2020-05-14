package com.juaracoding.attendanceapps.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.attendanceapps.ApiService.APIClient;
import com.juaracoding.attendanceapps.ApiService.APIInterfacesRest;
import com.juaracoding.attendanceapps.MainActivity;
import com.juaracoding.attendanceapps.R;
import com.juaracoding.attendanceapps.adapter.AdapterFace;
import com.juaracoding.attendanceapps.model.siswa.Datum;
import com.juaracoding.attendanceapps.model.siswa.SiswaByNis;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tracking extends Fragment {
    TextView txtName, txtTime;
    ImageView imgLocation;
    Button btnDetail;
    VideoView vStream;
    RecyclerView rvImgFaces;

    private onFragmentBtnSelected listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tracking, container, false);

        txtName = view.findViewById(R.id.txtName);
        txtTime = view.findViewById(R.id.txtTime);
        imgLocation = view.findViewById(R.id.imgLocation);
        btnDetail = view.findViewById(R.id.btnDetail);
        vStream = view.findViewById(R.id.vStream);
        rvImgFaces = view.findViewById(R.id.rvImgFaces);

//      creating media controller for vStream
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(vStream);
//      URI video
        Uri uri = Uri.parse("192.168.43.95");

//      Setting Media Controller and URI, then starting Video
        vStream.setMediaController(mediaController);
        vStream.setVideoURI(uri);
        vStream.requestFocus();
        vStream.start();

        String nisSiswa = getActivity().getIntent().getStringExtra("nis_siswa");


//      btnDetail on click
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonDetailSelected();
            }
        });

        getSiswa(nisSiswa);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentBtnSelected) {
            listener = (onFragmentBtnSelected) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface onFragmentBtnSelected {
        public void onButtonDetailSelected();
    }

    //  api get siswa
    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void getSiswa(String nis){

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

//                  split string name
                    String name = dataSiswa.getData().get(0).getNamaSiswa();
                    String[] separated = name.split(" ");

//                  Date Format
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh-mm");
//                    String dateTime = simpleDateFormat.format(dataSiswa.getData().get(0).getJamMasuk());

                    txtName.setText(separated[0]);
                    txtTime.setText(dataSiswa.getData().get(0).getJamMasuk());

                    String imageLocation = "http://192.168.43.38:5000/download/" + dataSiswa.getData().get(0).getLokasiTerakhir();
                    Picasso.get().load(imageLocation).into(imgLocation);

                    AdapterFace adapterFace = new AdapterFace(dataSiswa.getData(), getActivity());
                    rvImgFaces.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                    rvImgFaces.setItemAnimator(new DefaultItemAnimator());
                    rvImgFaces.setAdapter(adapterFace);

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

