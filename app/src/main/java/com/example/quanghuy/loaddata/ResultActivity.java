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

public class ResultActivity extends AppCompatActivity {
    String DATABASE_NAME = "ResultDB.sqlite";
    SQLiteDatabase database;
    private Context context;

    ListView listView;
    TextView textViewtfrc;
    ArrayList<KetQuaChiTiet> list;
    AdapterKetQuaChiTiet adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        context = this;

        textViewtfrc = (TextView) findViewById(R.id.textViewtfrc);
        // nhan tham so tu CategoriesActivity
        Bundle bd = getIntent().getExtras();
        if (bd != null) {
            String Tfrc = bd.getString("tfrc");
            textViewtfrc.setText(Tfrc);
        }
        addControls();
        String s1 = textViewtfrc.getText().toString();
        String s2 = "SELECT * FROM KetQua WHERE ID = ";
        final String s3 = s2 + concat(s1);
     //   Toast.makeText(ResultActivity.this, s3, Toast.LENGTH_LONG).show();

        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery(s3, null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String thongtin = cursor.getString(1);
            list.add(new KetQuaChiTiet(id, thongtin));
        }
        adapter.notifyDataSetChanged();

    }

    private void addControls() {
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterKetQuaChiTiet(this, list);
        listView.setAdapter(adapter);
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
