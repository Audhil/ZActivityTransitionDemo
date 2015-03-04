package com.wordpress.smdaudhilbe.zmaterialactivitytransition;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends BaseActivity {

	private DrawerLayout drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarIcon(R.drawable.ic_ab_drawer);
		
		GridView gridView = (GridView)findViewById(R.id.gridView);
		gridView.setAdapter(new GridViewAdapter());
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				String url = (String) view.getTag();
                DetailActivity.launch(MainActivity.this, view.findViewById(R.id.image), url); 
			}			
		});
		
		drawer = (DrawerLayout)findViewById(R.id.drawer);
		drawer.setDrawerShadow(R.drawable.drawer_shadow,Gravity.START);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			drawer.openDrawer(Gravity.START);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_main;
	}

	// grid view adapter
	private static class GridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object getItem(int position) {
			return "Item "+String.valueOf(position + 1);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null){
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
			}
			
			String imageUrl = "http://lorempixel.com/800/600/sports/" + String.valueOf(position + 1);
			convertView.setTag(imageUrl);
			
			ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
			Picasso.with(convertView.getContext()).load(imageUrl).into(imageView);
			
			TextView text = (TextView) convertView.findViewById(R.id.text);
            text.setText(getItem(position).toString());
			
			return convertView;
		}
	}
	
	@Override
	public void onBackPressed() {
		if(drawer != null && drawer.isDrawerOpen(Gravity.START)){
			drawer.closeDrawer(Gravity.START);
		}
		else{
			super.onBackPressed();
		}
	}
}