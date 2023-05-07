/**
 * ProjectName:
 * PackageName:net.shopnc.android.model
 * FileNmae:MyAsynaTask.java
 */
package com.common.util;

import com.blueberry.activity.R;
import de.hdodenhof.circleimageview.CircleImageView;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 异步加载图片（Background）
 * @author KingKong·HE
 */
public class MyBackAsynaTask extends AsyncTask<String,Void,String>{
	private String themb;
	private CircleImageView iv;

	public MyBackAsynaTask(String themb,CircleImageView iv){
		this.themb=themb;
		this.iv=iv;
	}
	@Override
	protected String doInBackground(String... params) {
		if(themb!=null){
			return themb;
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(result!=null && !"".equals(result)&& !"null".equals(result)){
//			//加载远程图片
			ImageLoader.getInstance().asyncLoadBitmap(result, new ImageLoader.ImageCallback() {
				@Override
				public void imageLoaded(Bitmap bmp, String url) {
					if(bmp!=null){
						//Bitmap bitmap=getBitmap(bmp, bmp.getWidth());
						iv.setBackgroundDrawable(new BitmapDrawable(bmp));
					}else{
						iv.setBackgroundResource(R.drawable.main_banner);
					}
				}
			});
		}else{
			iv.setBackgroundResource(R.drawable.main_banner);
		}
	}
	/***
	 * 对图片处理
	 * @author zhangjia
	 */
	public Bitmap getBitmap(Bitmap bitmap, int width) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scale = (float) width / w;
		System.out.println("scale-->" + scale);
		// 保证图片不变形.
		matrix.postScale(scale, scale);
		// w,h是原图的属性.
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	}