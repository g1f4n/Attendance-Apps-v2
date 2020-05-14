
package com.juaracoding.attendanceapps.model.orangtua;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginOrtu implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("nama_ortu")
    @Expose
    private String namaOrtu;
    @SerializedName("nis_siswa")
    @Expose
    private String nisSiswa;
    public final static Creator<LoginOrtu> CREATOR = new Creator<LoginOrtu>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LoginOrtu createFromParcel(Parcel in) {
            return new LoginOrtu(in);
        }

        public LoginOrtu[] newArray(int size) {
            return (new LoginOrtu[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1567496466655496172L;

    protected LoginOrtu(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.namaOrtu = ((String) in.readValue((String.class.getClassLoader())));
        this.nisSiswa = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginOrtu() {
    }

    /**
     * 
     * @param nisSiswa
     * @param namaOrtu
     * @param message
     */
    public LoginOrtu(String message, String namaOrtu, String nisSiswa) {
        super();
        this.message = message;
        this.namaOrtu = namaOrtu;
        this.nisSiswa = nisSiswa;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public String getNisSiswa() {
        return nisSiswa;
    }

    public void setNisSiswa(String nisSiswa) {
        this.nisSiswa = nisSiswa;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(namaOrtu);
        dest.writeValue(nisSiswa);
    }

    public int describeContents() {
        return  0;
    }

}
