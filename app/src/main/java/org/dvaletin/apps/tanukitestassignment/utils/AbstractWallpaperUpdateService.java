package org.dvaletin.apps.tanukitestassignment.utils;

import java.io.InputStream;

import android.content.Context;

public interface AbstractWallpaperUpdateService {

	public void init(Context context);

	public InputStream getNextImage();

	public boolean update();

}
