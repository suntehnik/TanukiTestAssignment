package org.dvaletin.apps.tanukitestassignment;

import org.dvaletin.apps.tanukitestassignment.utils.CategoryContent;
import org.dvaletin.apps.tanukitestassignment.utils.WebImageAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * A fragment representing a single Picture detail screen. This fragment is
 * either contained in a {@link The500PxActivity} in two-pane mode (on
 * tablets) or a {@link CategoryImageListActivity} on handsets.
 */
public class CategoryImageListFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "category_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private int categoryId = -1;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public CategoryImageListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			categoryId = getArguments().getInt(
					ARG_ITEM_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_category_image_list,
				container, false);

		// Show the dummy content as text in a TextView.
		if (categoryId != -1) {
			CategoryContent.init();
			GridView gv = (GridView) rootView.findViewById(R.id.WebimagesGrid);
			gv.setAdapter(new WebImageAdapter(getActivity(), R.layout.item_webimage, R.id.Webimage, R.id.WebimageTitile, R.id.WebimageAuthor, categoryId));
			gv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView, View view,
						int position, long id) {
					WebImageAdapter.PhotoItem item = (WebImageAdapter.PhotoItem)adapterView.getAdapter().getItem(position);
					Intent intent = new Intent(getActivity(), WebimageActivity.class);
					intent.putExtra(WebimageFragment.ARG_IMAGEID, (int) id);
					intent.putExtra(WebimageFragment.ARG_TITLE, item.title);
					intent.putExtra(WebimageFragment.ARG_AUTHOR, item.author);
					startActivity(intent);
				}});
		}

		return rootView;
	}
}
