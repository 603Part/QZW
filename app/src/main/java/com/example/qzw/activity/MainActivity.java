package com.example.qzw.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qzw.R;
import com.example.qzw.adapter.ListviewPoetryAdapter;
import com.example.qzw.bean.QianZiWen;
import com.example.qzw.db.DBManager;

import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lv;

    private List<QianZiWen.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lv = findViewById(R.id.lv);
        initData();
    }

    private void initData() {
        data = DBManager.query();
        lv.setAdapter(new ListviewPoetryAdapter(this, data));
        lv.setOnItemClickListener(this);
    }

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        StudyActivity.lauchActivity(this,data.get(i).getContent(),data.get(i).getTranslate(),data.get(i).getTips());
    }
}
