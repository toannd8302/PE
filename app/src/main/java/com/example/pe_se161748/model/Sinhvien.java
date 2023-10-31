package com.example.pe_se161748.model;

import android.os.Parcel;
import android.os.Parcelable;




//Class chage theo table of Mock API
public class Sinhvien implements Parcelable {
    private String id;
    private String name;
    private String dob;
    private String gender;
    private String address;


    public Sinhvien(String id, String name, String dob, String gender, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public Sinhvien(String name, String dob, String gender, String address) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    protected Sinhvien(Parcel in) {
        id = in.readString();
        name = in.readString();
        dob = in.readString();
        gender = in.readString();
        address = in.readString();
    }

    public static final Creator<Sinhvien> CREATOR = new Creator<Sinhvien>() {
        @Override
        public Sinhvien createFromParcel(Parcel in) {
            return new Sinhvien(in);
        }

        @Override
        public Sinhvien[] newArray(int size) {
            return new Sinhvien[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(dob);
        parcel.writeString(gender);
        parcel.writeString(address);
    }

}
