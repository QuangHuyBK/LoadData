package com.example.quanghuy.loaddata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 3/30/2017.
 */

public class AdapterKetQuaChiTiet extends BaseAdapter {
    Context context;
    ArrayList<KetQuaChiTiet> list;

    public AdapterKetQuaChiTiet(Context context, ArrayList<KetQuaChiTiet> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row, null);
        TextView textviewKetqua = (TextView) row.findViewById(R.id.textViewKetqua);


        KetQuaChiTiet ketQuaChiTiet = list.get(position);
        textviewKetqua.setText(ketQuaChiTiet.thongtin);

        return row;


    }
}
