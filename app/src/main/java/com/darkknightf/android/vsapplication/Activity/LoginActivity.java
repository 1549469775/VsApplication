package com.darkknightf.android.vsapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darkknightf.android.vsapplication.R;

import cn.bmob.v3.Bmob;

public class LoginActivity extends AppCompatActivity {

    private static final String BMOB_APP_KEY="f8c3bf8bdf24fada23bff3a1a965529f";
    private EditText ed_name;
    private EditText ed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化Bmob sdk
        Bmob.initialize(this, BMOB_APP_KEY);
        init();
    }
    private void init(){

        Button denglu = (Button) findViewById(R.id.btn_login);
        TextView zhuce = (TextView) findViewById(R.id.tv_go_to_register);
        ed_name = (EditText) findViewById(R.id.et_user_account);
        ed_password = (EditText) findViewById(R.id.et_user_password);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,VsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_SHORT);
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent); 
            }
        });
    }

}
