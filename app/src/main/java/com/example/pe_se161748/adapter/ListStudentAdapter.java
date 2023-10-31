package com.example.pe_se161748.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pe_se161748.R;
import com.example.pe_se161748.model.Sinhvien;
import com.example.pe_se161748.model.Sinhvien;

import java.util.ArrayList;

public class ListStudentAdapter extends BaseAdapter {

    private ArrayList<Sinhvien> listStudent;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListStudentAdapter(ArrayList<Sinhvien> listStudent) {
        this.listStudent = listStudent;
    }

    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return listStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewStudent;
        if(convertView == null){
            viewStudent = View.inflate(parent.getContext(), R.layout.item_sinhvien ,null);
        }
        else{
            viewStudent = convertView;
        }
        Sinhvien sinhvien = (Sinhvien) getItem(position);
        ((TextView) viewStudent.findViewById(R.id.txtName)).setText(String.format("" + sinhvien.getName()));
        ((TextView) viewStudent.findViewById(R.id.txtDate)).setText(String.format("" + sinhvien.getDob()));
        ((TextView) viewStudent.findViewById(R.id.txtGender)).setText(String.format("" + sinhvien.getGender()));
        ((TextView) viewStudent.findViewById(R.id.txtAddress)).setText(String.format("" + sinhvien.getAddress()));

        return viewStudent;
    }
}
