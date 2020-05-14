
package com.juaracoding.attendanceapps.model.siswa;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiswaByNis implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    public final static Creator<SiswaByNis> CREATOR = new Creator<SiswaByNis>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SiswaByNis createFromParcel(Parcel in) {
            return new SiswaByNis(in);
        }

        public SiswaByNis[] newArray(int size) {
            return (new SiswaByNis[size]);
        }

    }
    ;
    private final static long serialVersionUID = -154671293825180398L;

    protected SiswaByNis(Parcel in) {
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SiswaByNis() {
    }

    /**
     * 
     * @param data
     * @param status
     */
    public SiswaByNis(List<Datum> data, Boolean status) {
        super();
        this.data = data;
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
