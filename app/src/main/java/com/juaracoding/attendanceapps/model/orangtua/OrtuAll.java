
package com.juaracoding.attendanceapps.model.orangtua;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrtuAll implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    public final static Creator<OrtuAll> CREATOR = new Creator<OrtuAll>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OrtuAll createFromParcel(Parcel in) {
            return new OrtuAll(in);
        }

        public OrtuAll[] newArray(int size) {
            return (new OrtuAll[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8026659851564794214L;

    protected OrtuAll(Parcel in) {
        in.readList(this.data, (com.juaracoding.attendanceapps.model.orangtua.Datum.class.getClassLoader()));
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrtuAll() {
    }

    /**
     * 
     * @param data
     * @param status
     */
    public OrtuAll(List<Datum> data, Boolean status) {
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
