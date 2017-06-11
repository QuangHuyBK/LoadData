package com.example.quanghuy.loaddata;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.concat;


public class UpdateResultActivity extends ActionBarActivity {

    private EditText editTextName;
    private EditText editTextHinh, editTextChung, editTextTfrc;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_result);
        context = this;


        editTextName = (EditText) findViewById(R.id.editTextUsername);
        editTextHinh = (EditText) findViewById(R.id.editTextUrlhinh);
        editTextChung = (EditText) findViewById(R.id.editTextTenchung);
        editTextTfrc = (EditText) findViewById(R.id.editTextTfrc);


    }

    public void insert(View view) {
        String Username = editTextName.getText().toString();
        String urlHinh = editTextHinh.getText().toString();
        String Tenchung = editTextChung.getText().toString();
        String TFRC = editTextTfrc.getText().toString();


        insertToDatabase(Username, urlHinh, Tenchung, TFRC);
    }

    private void insertToDatabase(String Username, String urlHinh, String Tenchung, String TFRC) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramurlHinh = params[1];
                String paramTenchung = params[2];
                String paramTFRC = params[3];


                String Username = editTextName.getText().toString();
                String s1 = editTextHinh.getText().toString();
                String s2 = "http://doantotnghiepbk.esy.es/uploadFile/upload_image_vantay/images/";
                final String s3 = s2 + concat(s1);
                String Tenchung = editTextChung.getText().toString();
                String TFRC = editTextTfrc.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Username", Username));
                nameValuePairs.add(new BasicNameValuePair("urlHinh", s3));
                nameValuePairs.add(new BasicNameValuePair("Tenchung", Tenchung));
                nameValuePairs.add(new BasicNameValuePair("TFRC", TFRC));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://doantotnghiepbk.esy.es/mobile/nguoidung/result_insert.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Username, urlHinh, Tenchung, TFRC);
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