package com.example.quanghuy.loaddata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by QuangHuy on 4/3/2017.
 */

public class AdapterKetQuaTinhCach extends BaseAdapter {
    Context context;
    ArrayList<KetQuaTinhCach> list;

    public AdapterKetQuaTinhCach(Context context, ArrayList<KetQuaTinhCach> list) {
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
        View row = inflater.inflate(R.layout.listview_row_tc, null);
        TextView textViewKetquatinhcach = (TextView) row.findViewById(R.id.textViewKetquatinhcach);

        KetQuaTinhCach ketQuaTinhCach = list.get(position);
        textViewKetquatinhcach.setText(ketQuaTinhCach.tinhcach);

        return row;

    }
}
