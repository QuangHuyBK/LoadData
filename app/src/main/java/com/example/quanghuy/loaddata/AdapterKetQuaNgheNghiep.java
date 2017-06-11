package com.example.quanghuy.loaddata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by QuangHuy on 4/5/2017.
 */

public class AdapterKetQuaNgheNghiep extends BaseAdapter{
    Context context;
    ArrayList<KetQuaNgheNghiep> list;

    public AdapterKetQuaNgheNghiep(Context context, ArrayList<KetQuaNgheNghiep> list) {
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
        View row = inflater.inflate(R.layout.listview_row_nn, null);
        ImageView imageViewNghenghiep = (ImageView) row.findViewById(R.id.imageViewNghenghiep);

        KetQuaNgheNghiep ketQuaNgheNghiep = list.get(position);

        Bitmap bmNgheNghiep = BitmapFactory.decodeByteArray(ketQuaNgheNghiep.nghenghiep , 0 , ketQuaNgheNghiep.nghenghiep.length);
        imageViewNghenghiep.setImageBitmap(bmNgheNghiep);
        return row;
    }
}
