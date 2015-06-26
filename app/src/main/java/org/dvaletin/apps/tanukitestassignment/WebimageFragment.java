package org.dvaletin.apps.tanukitestassignment;

import java.io.File;
import java.io.IOException;

import org.dvaletin.apps.tanukitestassignment.utils.The500PixAdapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link WebimageFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class WebimageFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	public static final String ARG_IMAGEID = "imageId";
	public static final String ARG_TITLE = "title";
	public static final String ARG_AUTHOR = "author";
	
	private static final String ARG_CACHED_FILE = "filePath";

	private int imageId;
	private ProgressBar progress;
	private String author;
	private String title;
	private String filePath;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment WebimageFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static WebimageFragment newInstance(int pImageId, String pAuthor, String pTitle) {
		WebimageFragment fragment = new WebimageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_IMAGEID, pImageId);
		args.putString(ARG_AUTHOR, pAuthor);
		args.putString(ARG_TITLE, pTitle);
		fragment.setArguments(args);
		return fragment;
	}

	public WebimageFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			imageId = getArguments().getInt(ARG_IMAGEID);
			author = getArguments().getString(ARG_AUTHOR);
			title = getArguments().getString(ARG_TITLE);
			filePath = getArguments().getString(ARG_CACHED_FILE);
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ARG_IMAGEID, imageId);
		outState.putString(ARG_AUTHOR, author);
		outState.putString(ARG_TITLE, title);
		if(filePath != null) {
			outState.putString(ARG_CACHED_FILE, filePath);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_webimage, container, false);
		
		((TextView) v.findViewById(R.id.WebimageDetailAuthor)).setText(author);
		((TextView) v.findViewById(R.id.WebimageDetailTitle)).setText(title);
		progress = (ProgressBar) v.findViewById(R.id.WebimageDetailProgress);
		ImageView webImage = (ImageView) v.findViewById(R.id.WebimageDetailImageView);
		imageDowloadTask.execute(webImage);
		return v;
	}
	
	
	private AsyncTask<ImageView, Void, Bitmap> imageDowloadTask = new AsyncTask<ImageView, Void, Bitmap>() {

		protected ImageView iv;
		protected The500PixAdapter server;
		@Override
		protected void onPreExecute() {
			server = The500PixAdapter.getInstance(-1);
		}
		@Override
		protected Bitmap doInBackground(ImageView... params) {
			iv = params[0];
			Bitmap toReturn;
			String path = WebimageFragment.this.getActivity().getExternalCacheDir() + File.separator + imageId + "_hr.jpg";
			if(( toReturn = BitmapFactory.decodeFile(path) ) != null) {
				return toReturn;
			}
			try {
				server.downloadImage(imageId, path, 4);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return BitmapFactory.decodeFile(path);
		}
		
		@Override 
		protected void onPostExecute(Bitmap result) {

			if( result != null) {
				iv.setImageBitmap(result);
			} else {
				iv.setImageResource(android.R.drawable.ic_delete);
			}
			progress.setVisibility(View.GONE);
			iv.setVisibility(View.VISIBLE);
		}
		
	};

}
