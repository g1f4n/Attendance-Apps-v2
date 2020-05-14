package com.juaracoding.attendanceapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juaracoding.attendanceapps.ApiService.APIClient;
import com.juaracoding.attendanceapps.ApiService.APIInterfacesRest;
import com.juaracoding.attendanceapps.MainActivity;
import com.juaracoding.attendanceapps.R;
import com.juaracoding.attendanceapps.model.orangtua.LoginOrtu;
import com.juaracoding.attendanceapps.model.orangtua.OrtuAll;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAuthentication(txtUsername.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

//  api get ortu
    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void getAuthentication(final String username, final String password){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<LoginOrtu> call3 = apiInterface.getAuthentication(username, password);
        call3.enqueue(new Callback<LoginOrtu>() {
            @Override
            public void onResponse(Call<LoginOrtu> call, Response<LoginOrtu> response) {
                progressDialog.dismiss();
                LoginOrtu dataOrtu = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataOrtu !=null) {

                    if (!txtUsername.getText().toString().isEmpty() || !txtPassword.getText().toString().isEmpty()) {
                        if (txtUsername.getText().toString().equals(username) && txtPassword.getText().toString().equals(password)) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("nama_ortu", dataOrtu.getNamaOrtu());
                            intent.putExtra("nis_siswa", dataOrtu.getNisSiswa());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Username atau Password anda salah", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Username dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Login.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginOrtu> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
