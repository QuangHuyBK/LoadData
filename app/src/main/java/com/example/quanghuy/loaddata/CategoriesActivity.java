package com.example.quanghuy.loaddata;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.text.TextUtils.concat;

public class CategoriesActivity extends AppCompatActivity {
    TextView textViewTongvan, textViewTenchung, textViewyourname1;
    TextView textViewHoctap, textViewTinhcach, textViewNghenghiep, textViewAboutus, textViewTiemnang, textViewChiso;
    ArrayList<KetQua> mang;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        context = this;

        textViewTongvan = (TextView) findViewById(R.id.textViewTongvan);
        textViewTenchung = (TextView) findViewById(R.id.textViewTenchung);
        textViewyourname1 = (TextView) findViewById(R.id.textViewyourname1);


        textViewHoctap = (TextView) findViewById(R.id.textViewHoctap);
        textViewHoctap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh4 = new Intent(CategoriesActivity.this, ResultActivity.class);
                mh4.putExtra("tfrc", textViewTongvan.getText().toString());
                startActivity(mh4);
            }
        });

        textViewTinhcach = (TextView) findViewById(R.id.textViewTinhcach);
        textViewTinhcach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh5 = new Intent(CategoriesActivity.this, ResultTinhCachActivity.class);
                mh5.putExtra("chung", textViewTenchung.getText().toString());
                startActivity(mh5);
            }
        });

        textViewNghenghiep = (TextView) findViewById(R.id.textViewNghenghiep);
        textViewNghenghiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh6 = new Intent(CategoriesActivity.this, ResultNgheNghiepActivity.class);
                mh6.putExtra("chung", textViewTenchung.getText().toString());
                startActivity(mh6);
            }
        });

        textViewAboutus = (TextView) findViewById(R.id.textViewAboutus);
        textViewAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh7 = new Intent(CategoriesActivity.this, WebActivity.class);
                startActivity(mh7);
            }
        });

        textViewTiemnang = (TextView) findViewById(R.id.textViewTiemnang);
        textViewTiemnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh8 = new Intent(CategoriesActivity.this, ResultTiemNangActivity.class);
                mh8.putExtra("chung", textViewTenchung.getText().toString());
                startActivity(mh8);
            }
        });

        textViewChiso = (TextView) findViewById(R.id.textViewChiso);
        textViewChiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh9 = new Intent(CategoriesActivity.this, ResultChiSoActivity.class);
                startActivity(mh9);
            }
        });

        // nhan tham so tu MainActivity
        Bundle bd = getIntent().getExtras();
        if (bd != null) {
            String ten = bd.getString("hoten1");
            textViewyourname1.setText(ten);
        }
// khoi tao chuoi lay link load du lieu
        String s1 = textViewyourname1.getText().toString();
        String s2 = "http://doantotnghiepbk.esy.es/mobile/nguoidung/json.php?test=true&Username=";
        final String s3 = s2 + concat(s1);

        mang = new ArrayList<KetQua>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute(s3);
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String chuoi = docNoiDung_Tu_URL(params[0]);
            return chuoi;
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(CategoriesActivity.this, s, Toast.LENGTH_LONG).show();

            try {
                JSONArray mangJSON = new JSONArray(s);
                JSONObject kq = mangJSON.getJSONObject(0);
                String root = String.valueOf(kq);
                //Toast.makeText(CategoriesActivity.this, root, Toast.LENGTH_LONG).show();

                // xử lý cắt chuỗi để lấy thông tin TFRC
                String result[] = root.split(",");
                String result1[] = result[4].split(":");
                String y1 = result1[1];
                String y2 = y1.substring(1);
                StringBuilder y3 = new StringBuilder(y2);
                String y4 = y3.reverse().toString();
                String y5 = y4.substring(2);
                StringBuilder y6 = new StringBuilder(y5);
                String y7 = y6.reverse().toString();

                textViewTongvan.setText(y7); // truyen chuoi vao textview

                // xử lý cắt chuỗi để lấy tên chủng
                String result2[] = root.split(",");
                String result3[] = result2[3].split(":");
                String x1 = result3[1];
                String x2 = x1.substring(1);
                StringBuilder x3 = new StringBuilder(x2);
                String x4 = x3.reverse().toString();
                String x5 = x4.substring(1);
                StringBuilder x6 = new StringBuilder(x5);
                String x7 = x6.reverse().toString();
                textViewTenchung.setText(x7);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
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
