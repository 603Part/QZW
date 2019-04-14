package com.example.qzw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qzw.R;

public abstract class BaseActivity extends AppCompatActivity {
    private ImageView back;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());

        back = findViewById(R.id.back);
        title = findViewById(R.id.title);

        try {
            title.setText(getTitle());
        } catch (Exception e) {
            title.setText("");
            e.printStackTrace();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void setBackInVisiable() {
        back.setVisibility(View.INVISIBLE);
    }

    protected void setTitle(String titleContent) {
        title.setText(titleContent);
    }

    protected abstract int layout();
}
