package com.example.qzw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.example.qzw.R;
import com.example.qzw.utils.AssetsUtils;

public class HistoryActivity extends BaseActivity {
    private TextView history;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        history = findViewById(R.id.history);
        initData();
    }

    private void initData() {
        String fromAssets = AssetsUtils.getFromAssets("qzw_story.txt", this);
        history.setText(Html.fromHtml(fromAssets));
    }

    @Override
    protected int layout() {
        return R.layout.activity_history;
    }


}
