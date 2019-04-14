package com.example.qzw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qzw.R;
import com.example.qzw.bean.User;
import com.example.qzw.db.DBManager;

public class RegistActivity extends AppCompatActivity {

    private static final String TAG = "RegistActivity";

    private EditText username, pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();
    }

    private void initView() {
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
    }



    public void register(View view) {
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pwd.getText().toString())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        int i = DBManager.insertNewUser(new User(username.getText().toString(), pwd.getText().toString()));
        String str = "未知错误";
        switch (i) {
            case 0:
                str = "注册成功";
                break;
            case 1:
                str = "用户已存在";
                break;
            case 2:
                str = "注册失败请稍后重试";
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        finish();
    }
}
