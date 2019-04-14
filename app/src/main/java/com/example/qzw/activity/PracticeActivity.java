package com.example.qzw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class PracticeActivity extends BaseActivity implements View.OnClickListener{
    private TextView content,tips,translate,content_1_first,content_2_first;
    private Button submit,last,next;
    private LinearLayout rawContent,rawTips,rawTranslate,content_2,content_1;
    private EditText content_1_first_input, content_2_first_input;

    List<QianZiWen.DataBean> dataBeans;
    private int totalSize;
    private int current;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        content = findViewById(R.id.content);
        tips = findViewById(R.id.tips);
        translate = findViewById(R.id.translate);

        content_1_first = findViewById(R.id.content_1_first);
        content_2_first = findViewById(R.id.content_2_first);

        submit = findViewById(R.id.submit);
        last = findViewById(R.id.last);
        next = findViewById(R.id.next);

        rawContent = findViewById(R.id.raw_content);
        rawTips = findViewById(R.id.raw_tips);
        rawTranslate = findViewById(R.id.raw_translate);

        content_1_first_input = findViewById(R.id.content_1_first_input);
        content_2_first_input = findViewById(R.id.content_2_first_input);

        content_1 = findViewById(R.id.content_1);
        content_2 = findViewById(R.id.content_2);
        contentContainer(View.INVISIBLE);
    }

    private void contentContainer(int invisible) {
        rawContent.setVisibility(invisible);
        rawTips.setVisibility(invisible);
        rawTranslate.setVisibility(invisible);
    }

    private void initListener() {
        submit.setOnClickListener(this);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    private void initData() {
        dataBeans = DBManager.query();
        Collections.shuffle(dataBeans);
        totalSize = dataBeans.size();
        current = 0;
//        String contentData = getIntent().getStringExtra("content");
//        String translateData = getIntent().getStringExtra("translate");
//        String tipsData = getIntent().getStringExtra("tips");

        QianZiWen.DataBean currentData = dataBeans.get(current);
        changeData(currentData.getContent(),currentData.getTranslate(),currentData.getTips());
        parseData(currentData.getContent());
    }

    private void parseData(String data) {
        if (data.length() == 20) {
            content_1_first.setText(data.substring(0, 5));
            content_2_first.setText(data.substring(10, 15));
        } else {
            content_1_first.setText(data.substring(0, 5));
            content_2.setVisibility(View.INVISIBLE);
        }
//        content_1_first.setText();
//        content_2_first.setText();
    }

    private void changeData(String contentData, String translateData, String tipsData) {
        content.setText(contentData);
        tips.setText(tipsData);
        translate.setText(translateData);
    }

    private void reset() {
        content_1_first_input.setText("");
        content_2_first_input.setText("");
    }

    @Override
    protected int layout() {
        return R.layout.activity_practice;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                contentContainer(View.VISIBLE);
                break;
            case R.id.next:
                if (current == totalSize - 1) {
                    Toast.makeText(this, "已经是最后一个了", Toast.LENGTH_SHORT).show();
                    return;
                }
                current += 1;
                contentContainer(View.INVISIBLE);
                QianZiWen.DataBean nextData = dataBeans.get(current);
                changeData(nextData.getContent(),nextData.getTranslate(),nextData.getTips());
                parseData(nextData.getContent());
                reset();
                break;
            case R.id.last:
                if (current == 0) {
                    Toast.makeText(this, "已经是第一个了", Toast.LENGTH_SHORT).show();
                    return;
                }
                current -= 1;
                contentContainer(View.INVISIBLE);
                QianZiWen.DataBean lastData = dataBeans.get(current);
                changeData(lastData.getContent(),lastData.getTranslate(),lastData.getTips());
                parseData(lastData.getContent());
                reset();
                break;
        }
    }
}
