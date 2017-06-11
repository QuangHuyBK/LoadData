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

public class ResultTiemNangActivity extends AppCompatActivity {
    private Context context;
    String DATABASE_NAME = "ResultTiemNangDB.sqlite";
    SQLiteDatabase database;
    TextView textViewChung;

    ListView listViewtn;
    ArrayList<KetQuaTiemNang> list;
    AdapterKetQuaTiemNang adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tiem_nang);
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
        String s3 = "SELECT * FROM KetQua3 WHERE Chung = ";
        final String s4 = s3 + concat(s2);

        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery( s4 , null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String chung = cursor.getString(1);
            String tiemnang = cursor.getString(2);
            list.add( new KetQuaTiemNang(id, chung, tiemnang));
        }
        adapter.notifyDataSetChanged();

    }
    private void addControls() {
        listViewtn = (ListView) findViewById(R.id.listViewtn);
        list = new ArrayList<>();
        adapter = new AdapterKetQuaTiemNang(this, list);
        listViewtn.setAdapter(adapter);
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


