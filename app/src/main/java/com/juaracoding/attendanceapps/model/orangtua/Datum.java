
package com.juaracoding.attendanceapps.model.orangtua;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable, Parcelable
{

    @SerializedName("nama_ortu")
    @Expose
    private String namaOrtu;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("username")
    @Expose
    private String username;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1854262102013675050L;

    protected Datum(Parcel in) {
        this.namaOrtu = ((String) in.readValue((String.class.getClassLoader())));
        this.nis = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param namaOrtu
     * @param password
     * @param nis
     * @param username
     */
    public Datum(String namaOrtu, String nis, String password, String username) {
        super();
        this.namaOrtu = namaOrtu;
        this.nis = nis;
        this.password = password;
        this.username = username;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(namaOrtu);
        dest.writeValue(nis);
        dest.writeValue(password);
        dest.writeValue(username);
    }

    public int describeContents() {
        return  0;
    }

}
