package com.example.quanghuy.loaddata;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtPass;
    Button btnLogin,btnCancel;
    ImageView imageViewbk;
    TextView textViewad, textViewcheckinternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        imageViewbk = (ImageView) findViewById(R.id.imageViewbk);
        edtName = (EditText) findViewById(R.id.edtUsername);
        edtPass = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnCancel= (Button)findViewById(R.id.buttonCancel);
        textViewad = (TextView) findViewById(R.id.textViewLoginadmin);
        textViewad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh = new Intent(LoginActivity.this, LoginAdminActivity.class);
                startActivity(mh);
            }
        });

        imageViewbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Phạm Quang Huy, Đại học Bách Khoa Hà Nội", Toast.LENGTH_SHORT).show();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().equals("") == true){
                    Toast.makeText(getApplicationContext(),R.string.loginerror, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.loginsuccess, Toast.LENGTH_SHORT).show();
                    Intent mh2 = new Intent(LoginActivity.this, MainActivity.class);
                    mh2.putExtra("hoten",edtName.getText().toString());
                    startActivity(mh2);
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initView(){
        textViewcheckinternet = (TextView) findViewById(R.id.textViewchecknetwwork);
        textViewcheckinternet.setOnClickListener(CheckNetWork_Click);
    }
    View.OnClickListener CheckNetWork_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isConnected() == true) {
                ViewDialog viewDialog = new ViewDialog();
                viewDialog.showDialog(LoginActivity.this, "Thiết bị của bạn đã kết nối Internet",1);

            } else {
                ViewDialog viewDialog = new ViewDialog();
                viewDialog.showDialog(LoginActivity.this, "Thiết bị của bạn không được kết nối Internet \n hãy kết nối wifi hoặc 3g và thử lại",2);
            }
        }
    };

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}



