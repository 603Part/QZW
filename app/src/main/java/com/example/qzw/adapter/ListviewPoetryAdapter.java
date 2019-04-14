package com.example.qzw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qzw.R;
import com.example.qzw.bean.QianZiWen;

import java.util.List;

public class ListviewPoetryAdapter extends BaseAdapter {
	
	private Context context;
	public List<QianZiWen.DataBean> list;
	private LayoutInflater layoutInflater;

	public ListviewPoetryAdapter(Context context, List<QianZiWen.DataBean> list) {
		
		this.context = context;
		this.list =list;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;  
        if(convertView ==null){
        	holder = new ViewHolder();  
        	convertView = layoutInflater.inflate(R.layout.item_poem, null);
        	holder.title = (TextView) convertView.findViewById(R.id.title);
//        	holder.writername = (TextView) convertView.findViewById(R.id.writername);
        	convertView.setTag(holder);
        }else{
        	holder = (ViewHolder) convertView.getTag();
        }
		holder.title.setText(list.get(position).getContent());
        

        
		return convertView;
	}
	
    public final class  ViewHolder {  
	        TextView title;
	        TextView writername;
	        
	    } 

}
