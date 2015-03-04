package com.wordpress.smdaudhilbe.zmaterialactivitytransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends BaseActivity {

	public static final String EXTRA_IMAGE = "DetailActivity:image";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView image = (ImageView) findViewById(R.id.imageDetail);
		ViewCompat.setTransitionName(image, EXTRA_IMAGE);
		Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(image);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_detail;
	}

	public static void launch(BaseActivity activity, View transitionView,String url) {
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView,EXTRA_IMAGE);
		Intent intent = new Intent(activity, DetailActivity.class);
		intent.putExtra(EXTRA_IMAGE, url);
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}
}