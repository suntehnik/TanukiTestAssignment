package org.dvaletin.apps.tanukitestassignment.utils;

import android.util.Log;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class The500PixAdapter {

	private static final String TAG = The500PixAdapter.class.getSimpleName();

	JsonHttpClient server;
	private String serverBaseUrl;
	private String secret;
	private int categoryId;

	
	public static The500PixAdapter getInstance(int pCategoryId) {
		return new The500PixAdapter("https://api.500px.com/v1/", Consts.API_KEY, pCategoryId);
	}
	
	public The500PixAdapter(String base, String secret, int pCategoryId) {
		this.serverBaseUrl = base;
		this.secret = secret;
		this.categoryId = pCategoryId;
		server = new JsonHttpClient();
	}

	public JSONObject updateImages(int page) {
		try {
			page++;
			String request = new String(serverBaseUrl);
			request += "photos?";
			if (page > 0) {
				request += "&page=" + page;
			}
			
			request += "&only=" + URLEncoder.encode(CategoryContent.categories.get(categoryId), "UTF-8");
			request += "&consumer_key=" + secret;
			Log.d(TAG, request);
			return server.request(new HttpGet(request));
		} catch (JSONException e) {
			Log.d(TAG, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
		}
		return null;
	}

	private InputStream downloadImage(long id, int size) {

		try {
			String request = new String(serverBaseUrl);
			request += "photos/" + id + "?";
			request += "image_size=" + size;
			request += "&consumer_key=" + secret;
			// Log.d(T, request);
			JSONObject image = server.request(new HttpGet(request));
			String url = image.getJSONObject("photo").getString("image_url");
			return server.requestFile(url);
		} catch (JSONException e) {
			Log.d(TAG, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
		}
		return null;

	}
	
	public void downloadImage(long id, String path, int size) throws IOException {
		File downloadFile = new File(path);
		if(downloadFile.exists()) {
			downloadFile.delete();
		}
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downloadFile));
		byte buffer[] = new byte[1024] ;
		BufferedInputStream bis = new BufferedInputStream(downloadImage(id, size));
		int count = 0;
		while( (count = bis.read(buffer, 0, 1024)) > 0) {
			bos.write(buffer, 0, count);
		}
		bis.close();
		bos.close();
		
	}

}
