package org.dvaletin.apps.tanukitestassignment.utils;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




/**
 * Adapter for Webimage. 
 * @author valetin
 *
 */
public class WebimageAdapter extends BaseAdapter {

	private Context context;
	private int layoutId;
	private int titleResourceId;
	private int authorResourceId;
	private int imagevewResourceId;
	
	
	private SparseArrayCompat<PhotoItem> photoItems;
	
	
	private The500PixAdapter server;
	
	/**
	 * WebimageAdapter constructor
	 * @param ctx - application context
	 * @param layout - layout id, like R.layout.xyz. The layout must contain ImageView, TextView, TextView, which resourceIDs must be passed next
	 * @param imagevewResourceId - ImageView resource id in @param layout
	 * @param titleResourceId - title TextView resource id in @param layout
	 * @param authorResourceId - author TextView resource id in @param layout
	 */
	public WebimageAdapter(Context ctx, int layout, int imagevewResourceId, int titleResourceId, int authorResourceId, int categoryId) {
		this.titleResourceId = titleResourceId;
		this.authorResourceId = authorResourceId;
		this.imagevewResourceId = imagevewResourceId;
		this.layoutId = layout;
		this.context = ctx;
		
		photoItems = new SparseArrayCompat<PhotoItem>();
		
		server = The500PixAdapter.getInstance(categoryId);
		adapterInitAsyncTask.execute();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photoItems.size();
	}

	@Override
	public PhotoItem getItem(int position) {
		// TODO Auto-generated method stub
		return photoItems.get((int) getItemId(position));
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return photoItems.keyAt(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iv = null;
		TextView title;
		TextView author;
		PhotoItem item = getItem(position);
		convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(this.layoutId, parent, false);
		
		iv = (ImageView) convertView.findViewById(this.imagevewResourceId);
		iv.setImageResource(android.R.drawable.picture_frame);
		item.setImageViewBitmap(iv);
		title = (TextView) convertView.findViewById(this.titleResourceId);
		title.setText(item.title);
		author = (TextView) convertView.findViewById(this.authorResourceId);
		author.setText(item.author);
		return convertView;
	}

	private AsyncTask<Void, Void, Void> adapterInitAsyncTask = new AsyncTask<Void, Void, Void>() {

		@Override
		protected Void doInBackground(Void... params) {
			JSONObject json = server.updateImages(0);
			try {
				JSONArray photos = json.getJSONArray("photos");
				for(int i = 0; i < photos.length(); i++) {
					try {
						JSONObject photo = photos.getJSONObject(i);
						int photoId = photo.getInt("id");
						String title = photo.getString("name");
						String author = photo.getJSONObject("user").getString("fullname");
						String url = photo.getString("image_url");
						photoItems.put(photoId, new PhotoItem(photoId, title, author, url));
					} catch (JSONException e) {
						
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			WebimageAdapter.this.notifyDataSetChanged();
			WebimageAdapter.this.notifyDataSetInvalidated();
		}
	};
	
	public class PhotoItem {
		public long id;
		public String title;
		public String author;
		public String cachePath;
		public String url;
		AsyncTask<ImageView, Void, String> cacheUpdateTask;
		
		
		
		public PhotoItem(long photoId, String pTitle, String pAuthor, String pUrl) {
			id = photoId;
			this.title = pTitle;
			this.author = pAuthor;
			this.url = pUrl;
			cachePath = context.getExternalCacheDir().getAbsolutePath() + File.separator + id + ".jpg";
		}

		public void setImageViewBitmap(ImageView v) {
			File cachedPhotoFile = new File(cachePath);
			if(cachedPhotoFile.exists()) {
				Bitmap toSet = BitmapFactory.decodeFile(cachePath);
				if(toSet != null)
					v.setImageBitmap(toSet);
				else {
					// Bitmap image was not downloaded properly, delete it
					new File(cachePath).delete();
				}
			} else {
				// schedule cache update task
				cacheUpdateTask = new AsyncTask<ImageView, Void, String>() {
					ImageView iv;
					@Override
					protected String doInBackground(ImageView... params) {
						// TODO Auto-generated method stub
						iv = params[0];
						iv.setTag(PhotoItem.this);
						try {
							server.downloadImage(id, cachePath, 1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "";
						}
						return cachePath;
					}
					
					@Override
					protected void onPostExecute(String value) {
						Bitmap toSet = BitmapFactory.decodeFile(value);
						if(toSet != null)
							iv.setImageBitmap(toSet);
						else {
							
							// Bitmap image was not downloaded properly, delete it
							new File(value).delete();
						}
					}
				};
				cacheUpdateTask.execute(v);
			}
		}
		
		public void cleanup() {
			if(cacheUpdateTask != null && !cacheUpdateTask.isCancelled()) {
				cacheUpdateTask.cancel(false);
				cacheUpdateTask = null;
			}
		}
	}
}
