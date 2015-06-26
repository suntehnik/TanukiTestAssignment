package org.dvaletin.apps.tanukitestassignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class WebimageActivity extends FragmentActivity {

	private int imageId = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webimage);
		// Show the Up button in the action bar.
		setupActionBar();
		if(getIntent().hasExtra(WebimageFragment.ARG_IMAGEID)) {
			imageId = getIntent().getExtras().getInt(WebimageFragment.ARG_IMAGEID);
			String author = getIntent().getExtras().getString(WebimageFragment.ARG_AUTHOR);
			String title = getIntent().getExtras().getString(WebimageFragment.ARG_TITLE);
			setTitle(title + " by " + author);
			Fragment f = WebimageFragment.newInstance(imageId, author, title);
			getSupportFragmentManager().beginTransaction().add(R.id.ActivityWebimageFragmentHolder, f).commit();
		}
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

}
