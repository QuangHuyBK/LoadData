package com.example.quanghuy.loaddata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAdminActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtPass;
    Button btnLogin,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        edtName = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel= (Button)findViewById(R.id.buttonCancel);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "phamquanghuy";
                String password = "20171";
                if (edtName.getText().toString().equals(username) && edtPass.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), R.string.loginsuccess, Toast.LENGTH_LONG).show();
                    Intent mh2 = new Intent(LoginAdminActivity.this, UpdateResultActivity.class);
                    mh2.putExtra("hoten",edtName.getText().toString());
                    startActivity(mh2);
                } else {
                    Toast.makeText(getApplicationContext(),R.string.loginerror, Toast.LENGTH_LONG).show();
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
}