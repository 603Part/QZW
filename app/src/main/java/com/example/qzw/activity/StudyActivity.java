package com.example.qzw.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.qzw.R;

public class StudyActivity extends BaseActivity {
    private TextView content,tips,translate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = findViewById(R.id.content);
        tips = findViewById(R.id.tips);
        translate = findViewById(R.id.translate);


        initData();
    }

    private void initData() {
        String contentData = getIntent().getStringExtra("content");
        String translateData = getIntent().getStringExtra("translate");
        String tipsData = getIntent().getStringExtra("tips");

        content.setText(contentData);
        tips.setText(tipsData);
        translate.setText(translateData);
    }

    @Override
    protected int layout() {
        return R.layout.activity_study;
    }

    public static void lauchActivity(Context context, String content,String translate,String tips) {
        Intent intent = new Intent(context, StudyActivity.class);
        intent.putExtra("content", content);
        intent.putExtra("translate", translate);
        intent.putExtra("tips", tips);
        context.startActivity(intent);
    }
}
