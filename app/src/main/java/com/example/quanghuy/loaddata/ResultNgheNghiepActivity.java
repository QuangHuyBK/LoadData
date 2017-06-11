package com.example.quanghuy.loaddata;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.text.TextUtils.concat;

public class ResultNgheNghiepActivity extends AppCompatActivity {
    private Context context;

    TextView textViewChung;
    String DATABASE_NAME = "ResultNgheNghiepDB.sqlite";
    SQLiteDatabase database;

    ListView listViewnn;
    ArrayList<KetQuaNgheNghiep> list;
    AdapterKetQuaNgheNghiep adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_nghe_nghiep);
        context = this;

        // nhan tham so tu CategoriesActivity
        textViewChung = (TextView) findViewById(R.id.textViewChung);
        Bundle bd = getIntent().getExtras();
        if (bd != null) {
            String Chungloai = bd.getString("chung");
            textViewChung.setText(Chungloai);
        }
        addControls();

        String s1 = textViewChung.getText().toString();
        String s2 =  "'"+s1+"'";
        String s3 = "SELECT * FROM KetQua2 WHERE Chung = ";
        final String s4 = s3 + concat(s2);


        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery( s4, null);
        list.clear();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String chung = cursor.getString(1);
            byte[] nghenghiep = cursor.getBlob(2);
            list.add(new KetQuaNgheNghiep(id, chung, nghenghiep));

           // list.add( new KetQuaTinhCach(id, chung, tinhcach));
        }
        adapter.notifyDataSetChanged();


    }

    private void addControls() {
        listViewnn = (ListView) findViewById(R.id.listViewnn);
        list = new ArrayList<>();
        adapter = new AdapterKetQuaNgheNghiep(this,list);
        listViewnn.setAdapter(adapter);
    }


    // Tạo menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
            case R.id.action_info:
                infomation();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void logout() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void infomation() {
        Intent intent1 = new Intent(context, AboutUsActivity.class);
        startActivity(intent1);
        finish();
    }
}
