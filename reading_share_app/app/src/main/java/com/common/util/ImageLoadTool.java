package com.common.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoadTool  extends AsyncTask<String, Void, Bitmap> {

	ImageView ivg;

	public ImageLoadTool(ImageView ivg) {
		// TODO Auto-generated constructor stub
		this.ivg = ivg;
	}

	@Override
	protected Bitmap doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		try {
			URL url = new URL(arg0[0]);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			InputStream isInputStream = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(isInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		ivg.setImageBitmap(result);
	}
}
