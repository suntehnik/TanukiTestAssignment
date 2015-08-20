package org.dvaletin.apps.tanukitestassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.dvaletin.apps.tanukitestassignment.inject.InjectAppCompatActivity;

/**
 * An activity representing a list of WebImages. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link CategoryImageListActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CategoryListFragment} and the item details (if present) is a
 * {@link CategoryImageListFragment}.
 * <p>
 * This activity also implements the required
 * {@link CategoryListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class The500PxActivity extends InjectAppCompatActivity implements
		CategoryListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webimage_list);

		if (findViewById(R.id.webimage_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((CategoryListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.webimage_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link CategoryListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(int id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putInt(CategoryImageListFragment.ARG_ITEM_ID, id);
			CategoryImageListFragment fragment = new CategoryImageListFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.webimage_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, CategoryImageListActivity.class);
			detailIntent.putExtra(CategoryImageListFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
