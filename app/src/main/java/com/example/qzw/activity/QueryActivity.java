package com.example.qzw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qzw.R;
import com.example.qzw.bean.QianZiWen;
import com.example.qzw.db.DBManager;

import java.util.Collections;
import java.util.List;

public class QueryActivity extends BaseActivity implements View.OnClickListener{
    private TextView content,tips,translate;
    private Button submit;
    private LinearLayout rawContent,rawTips,rawTranslate;
    private EditText content_1_first_input;

    List<QianZiWen.DataBean> dataBeans;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
    }

    private void initView() {
        content = findViewById(R.id.content);
        tips = findViewById(R.id.tips);
        translate = findViewById(R.id.translate);


        submit = findViewById(R.id.submit);

        rawContent = findViewById(R.id.raw_content);
        rawTips = findViewById(R.id.raw_tips);
        rawTranslate = findViewById(R.id.raw_translate);

        content_1_first_input = findViewById(R.id.content_1_first_input);

        contentContainer(View.INVISIBLE);
    }

    private void contentContainer(int invisible) {
        rawContent.setVisibility(invisible);
        rawTips.setVisibility(invisible);
        rawTranslate.setVisibility(invisible);
    }

    private void initListener() {
        submit.setOnClickListener(this);
    }

    private void changeData(String contentData, String translateData, String tipsData) {
        content.setText(contentData);
        tips.setText(tipsData);
        translate.setText(translateData);
    }

    @Override
    protected int layout() {
        return R.layout.activity_query;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (!TextUtils.isEmpty(content_1_first_input.getText().toString())) {
                    dataBeans = DBManager.query(content_1_first_input.getText().toString());

                    if (dataBeans != null && dataBeans.size() > 0) {
                        contentContainer(View.VISIBLE);
                        changeData(dataBeans.get(0).getContent(), dataBeans.get(0).getTranslate(), dataBeans.get(0).getTips());
                    } else {

                        Toast.makeText(this, "没有查到相关数据", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                Toast.makeText(this, "请输入要查询的字", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
