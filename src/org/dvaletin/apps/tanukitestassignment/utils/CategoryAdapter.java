package org.dvaletin.apps.tanukitestassignment.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CategoryAdapter extends android.widget.BaseAdapter {

	private Context context;
	public CategoryAdapter(Context ctx) {
		context = ctx;
		CategoryContent.init();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return CategoryContent.categories.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return CategoryContent.categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null) {
			convertView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(android.R.layout.simple_list_item_activated_1, parent, false);
		}
		
		((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position));
		return convertView;
	}

}
