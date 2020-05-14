
package com.juaracoding.attendanceapps.model.siswa;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable, Parcelable
{

    @SerializedName("data_wajah")
    @Expose
    private String dataWajah;
    @SerializedName("jam_keluar")
    @Expose
    private String jamKeluar;
    @SerializedName("jam_masuk")
    @Expose
    private String jamMasuk;
    @SerializedName("lokasi_terakhir")
    @Expose
    private String lokasiTerakhir;
    @SerializedName("nama_siswa")
    @Expose
    private String namaSiswa;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("ruang_kelas")
    @Expose
    private String ruangKelas;
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
    private final static long serialVersionUID = 4996814650343470128L;

    protected Datum(Parcel in) {
        this.dataWajah = ((String) in.readValue((String.class.getClassLoader())));
        this.jamKeluar = ((String) in.readValue((String.class.getClassLoader())));
        this.jamMasuk = ((String) in.readValue((String.class.getClassLoader())));
        this.lokasiTerakhir = ((String) in.readValue((String.class.getClassLoader())));
        this.namaSiswa = ((String) in.readValue((String.class.getClassLoader())));
        this.nis = ((String) in.readValue((String.class.getClassLoader())));
        this.ruangKelas = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param dataWajah
     * @param ruangKelas
     * @param lokasiTerakhir
     * @param jamKeluar
     * @param nis
     * @param namaSiswa
     * @param jamMasuk
     */
    public Datum(String dataWajah, String jamKeluar, String jamMasuk, String lokasiTerakhir, String namaSiswa, String nis, String ruangKelas) {
        super();
        this.dataWajah = dataWajah;
        this.jamKeluar = jamKeluar;
        this.jamMasuk = jamMasuk;
        this.lokasiTerakhir = lokasiTerakhir;
        this.namaSiswa = namaSiswa;
        this.nis = nis;
        this.ruangKelas = ruangKelas;
    }

    public String getDataWajah() {
        return dataWajah;
    }

    public void setDataWajah(String dataWajah) {
        this.dataWajah = dataWajah;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getLokasiTerakhir() {
        return lokasiTerakhir;
    }

    public void setLokasiTerakhir(String lokasiTerakhir) {
        this.lokasiTerakhir = lokasiTerakhir;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getRuangKelas() {
        return ruangKelas;
    }

    public void setRuangKelas(String ruangKelas) {
        this.ruangKelas = ruangKelas;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dataWajah);
        dest.writeValue(jamKeluar);
        dest.writeValue(jamMasuk);
        dest.writeValue(lokasiTerakhir);
        dest.writeValue(namaSiswa);
        dest.writeValue(nis);
        dest.writeValue(ruangKelas);
    }

    public int describeContents() {
        return  0;
    }

}
