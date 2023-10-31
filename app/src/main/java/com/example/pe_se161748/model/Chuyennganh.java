package com.example.pe_se161748.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Chuyennganh implements Parcelable {
    private String id;
    private String cnName;

    public Chuyennganh(String id, String cnName) {
        this.id = id;
        this.cnName = cnName;
    }

    public Chuyennganh(String cnName) {
        this.cnName = cnName;
    }

    protected Chuyennganh(Parcel in) {
        id = in.readString();
        cnName = in.readString();
    }

    public static final Creator<Chuyennganh> CREATOR = new Creator<Chuyennganh>() {
        @Override
        public Chuyennganh createFromParcel(Parcel in) {
            return new Chuyennganh(in);
        }

        @Override
        public Chuyennganh[] newArray(int size) {
            return new Chuyennganh[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(cnName);
    }
}
