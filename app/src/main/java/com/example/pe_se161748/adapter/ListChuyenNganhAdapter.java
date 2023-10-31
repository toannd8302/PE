package com.example.pe_se161748.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pe_se161748.R;
import com.example.pe_se161748.model.Chuyennganh;

import java.util.ArrayList;

public class ListChuyenNganhAdapter extends BaseAdapter {

    private ArrayList<Chuyennganh> listCN;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListChuyenNganhAdapter(ArrayList<Chuyennganh> listCN) {
        this.listCN = listCN;
    }

    @Override
    public int getCount() {
        return listCN.size();
    }

    @Override
    public Object getItem(int position) {
        return listCN.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCN;
        if(convertView == null){
            viewCN = View.inflate(parent.getContext(), R.layout.item_cn ,null);
        }
        else{
            viewCN = convertView;
        }
        Chuyennganh chuyennganh = (Chuyennganh) getItem(position);
        ((TextView) viewCN.findViewById(R.id.txtName)).setText(String.format("" + chuyennganh.getCnName()));

        return viewCN;
    }
}
