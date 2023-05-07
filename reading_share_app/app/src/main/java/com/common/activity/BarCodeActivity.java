/**
 * ProjectName:
 * PackageName:net.cangshengwang.android.ui.mystore
 * FileNmae:RegisteredActivity.java
 */
package com.common.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.blueberry.activity.R;
import com.common.util.MyApp;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.Encoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * @author KingKong·HE
 */
public class BarCodeActivity extends Activity {
	
	private ImageView imageBack,barcode;
	private Button buttonSend;
	private MyApp myApp;
	private int jobstatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_view);
		myApp = (MyApp) BarCodeActivity.this.getApplication();
		imageBack = (ImageView) findViewById(R.id.imageBack);
		barcode = (ImageView) findViewById(R.id.barcode);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		
		
		Bitmap bitmap =createBarcode(myApp.getLoginKey());
		barcode.setImageBitmap(bitmap);
		
		buttonSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		imageBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BarCodeActivity.this.finish();
			}
		});
	}
	
	 public static Bitmap createBarcode(String str) {  
	        Bitmap bitmap = null;  
	        BitMatrix result = null;  
	        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();  
	        try {  
	            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 400, 400);  
	  
	            int w = result.getWidth();  
	            int h = result.getHeight();  
	            int[] pixels = new int[w * h];  
	            for (int y = 0; y < h; y++) {  
	                int offset = y * w;  
	                for (int x = 0; x < w; x++) {  
	                    pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;  
	                }  
	            }  
	            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);  
	            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);  
	  
	  
	        } catch (WriterException e) {  
	            e.printStackTrace();  
	        } catch (IllegalArgumentException e) {  
	            e.printStackTrace();  
	        }  
	        return bitmap;  
	    }  
		

}
