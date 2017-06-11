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

public class ResultTinhCachActivity extends AppCompatActivity {
    private Context context;

    String DATABASE_NAME = "ResultTinhCachDB.sqlite";
    SQLiteDatabase database;
    TextView textViewChung;

    ListView listViewtc;
    ArrayList<KetQuaTinhCach> list;
    AdapterKetQuaTinhCach adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tinh_cach);
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
        String s3 = "SELECT * FROM KetQua1 WHERE Chung = ";
        final String s4 = s3 + concat(s2);
        // Toast.makeText(ResultTinhCachActivity.this, s4, Toast.LENGTH_LONG).show();

        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery( s4 , null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String chung = cursor.getString(1);
            String tinhcach = cursor.getString(2);
            list.add( new KetQuaTinhCach(id, chung, tinhcach));
        }
        adapter.notifyDataSetChanged();

//      database = Database.initDatabase(this, DATABASE_NAME);
//        Cursor cursor = database.rawQuery("SELECT * FROM  KetQua1",null);
//        cursor.moveToFirst();
//        Toast.makeText(this, cursor.getString(2), Toast.LENGTH_LONG).show();
    }


    private void addControls() {
        listViewtc = (ListView) findViewById(R.id.listViewtc);
        list = new ArrayList<>();
        adapter = new AdapterKetQuaTinhCach(this, list);
        listViewtc.setAdapter(adapter);
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
