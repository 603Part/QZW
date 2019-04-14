package com.example.qzw.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qzw.R;

import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private List<String> titles;

    public HomeAdapter(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(titles.get(i));
        return view;
    }

    public final class  ViewHolder {
        TextView title;

        public ViewHolder(View v) {
            title = v.findViewById(R.id.item_content);
        }
    }
}
