package com.example.qzw.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.qzw.R;
import com.example.qzw.adapter.HomeAdapter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private XBanner banner_1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackInVisiable();
        setTitle("首页");
        gridView = findViewById(R.id.main_gv);
        banner_1 = findViewById(R.id.banner_1);
        initView();
        initData();
    }

    private void initData() {
        final List<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.qzw);
        integers.add(R.drawable.qzw2);
        integers.add(R.drawable.qzw3);
        integers.add(R.drawable.qzw4);
        banner_1.setData(integers,null);
        banner_1.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView) view).setImageResource(integers.get(position));
            }
        });
    }

    private void initView() {
        List<String> strings = new ArrayList<>();
        strings.add("学习");
        strings.add("练习");
        strings.add("历史典故");
        strings.add("查询");
        strings.add("关于");
        HomeAdapter homeAdapter = new HomeAdapter(strings);
        gridView.setAdapter(homeAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected int layout() {
        return R.layout.activity_home;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        switch (i) {
            case 0:
                intent.setClass(this, MainActivity.class);
                break;
            case 1:
                intent.setClass(this, PracticeActivity.class);
                break;
            case 2:
                intent.setClass(this, HistoryActivity.class);
                break;
            case 3:
                intent.setClass(this, QueryActivity.class);
                break;
            case 4:
                intent.setClass(this, AboutActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

}
