package com.example.quanghuy.loaddata;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.text.TextUtils.concat;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    private Context context;

    Button buttonKetqua;
    TextView txtvHoten;
    ArrayList<KetQua> mang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;

        lv = (ListView) findViewById(R.id.listViewResult);
        txtvHoten = (TextView) findViewById(R.id.textViewyourname);

        // nhan tham so tu Loginactivity
        Bundle bd = getIntent().getExtras();
        if (bd != null) {
            String ten = bd.getString("hoten");
            txtvHoten.setText(ten);
        }

        /// truyen tham so Username sang Categories
        buttonKetqua = (Button) findViewById(R.id.buttonKetqua);
        buttonKetqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh3 = new Intent(MainActivity.this, CategoriesActivity.class);
                mh3.putExtra("hoten1", txtvHoten.getText().toString());
                startActivity(mh3);
            }
        });

        // khoi tao chuoi lay link load du lieu
        String s1 = txtvHoten.getText().toString();
        String s2 = "http://doantotnghiepbk.esy.es/mobile/nguoidung/json.php?test=true&Username=";
        final String s3 = s2 + concat(s1);
        String s4 = "Please! Waiting :) ";
        Toast.makeText(getApplicationContext(), s4, Toast.LENGTH_SHORT).show();
        mang = new ArrayList<KetQua>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute(s3);
            }
        });

    }


    // Đọc dữ liệu từ website
    class docJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray mangJSON = new JSONArray(s);
                for (int i = 0; i < mangJSON.length(); i++) {
                    JSONObject kq = mangJSON.getJSONObject(i);
                    mang.add(new KetQua(
                            // kq.getInt("id"),
                            kq.getString("Username"),
                            kq.getString("urlHinh"),
                            kq.getString("Tenchung"),
                            kq.getInt("TFRC")
                    ));
                }
                ListAdapter adapter = new ListAdapter(
                        getApplicationContext(),
                        R.layout.activity_dong_ket_qua,
                        mang
                );
                lv.setAdapter(adapter);

                //   Toast.makeText(getApplicationContext(), "" +  mang.size() , Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Toast.makeText(getApplicationContext(), s , Toast.LENGTH_LONG).show();
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
