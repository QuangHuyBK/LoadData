package com.example.quanghuy.loaddata;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends ArrayAdapter<KetQua> {

    public ListAdapter(Context context, int resource, List<KetQua> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_dong_ket_qua, null);
        }
        KetQua p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
//            TextView tt1 = (TextView) view.findViewById(R.id.textViewId);
//            tt1.setText(String.valueOf(p.id));

            TextView tt2 = (TextView) view.findViewById(R.id.textViewUsername);
            tt2.setText(p.Username);

            TextView tt3 = (TextView) view.findViewById(R.id.textViewChung);
            tt3.setText(String.valueOf(p.Tenchung));

            TextView tt4 = (TextView) view.findViewById(R.id.textViewTfrc);
            tt4.setText(String.valueOf(p.TFRC));

            ImageView imgv = (ImageView) view.findViewById(R.id.imageViewHinh);
            Picasso.with(getContext()).load(p.urlHinh).into(imgv);

        }
        return view;
    }

}