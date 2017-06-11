package com.example.quanghuy.loaddata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by QuangHuy on 4/8/2017.
 */

public class AdapterKetQuaTiemNang extends BaseAdapter {
    Context context;
    ArrayList<KetQuaTiemNang> list;

    public AdapterKetQuaTiemNang(Context context, ArrayList<KetQuaTiemNang> list) {
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
        View row = inflater.inflate(R.layout.listview_row_tn, null);
        TextView textViewKetquatiemnang = (TextView) row.findViewById(R.id.textViewKetquatiemnang);

        KetQuaTiemNang ketQuaTiemNang = list.get(position);
        textViewKetquatiemnang.setText(ketQuaTiemNang.tiemnang);

        return row;

    }
}
