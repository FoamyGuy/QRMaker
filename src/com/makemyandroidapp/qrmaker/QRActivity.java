package com.makemyandroidapp.qrmaker;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.ClipboardManager;
//import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class QRActivity extends Activity {
	ImageLoader imgLoader;
	ImageView qrImg;
	String copiedStr;
	TextView qrTxt;
	ClipboardManager clipboard;
	
	String BASE_QR_URL = "http://chart.apis.google.com/chart?cht=qr&chs=400x400&chld=M&choe=UTF-8&chl=";
	String fullUrl = BASE_QR_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);
        
        qrImg = (ImageView)findViewById(R.id.qrImg);
        qrTxt = (TextView)findViewById(R.id.qrTxt);
        

        
		clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		
		
		
        /*
         * clipboard.getText() is now deprecated. But I am going to use it here
         * because the new way of doing the same thing only works on API lvl 11+
         * Since I want this application to support API lvl 4+ we have to use
         * the old method.
         */
		CharSequence clipTxt = clipboard.getText();
		
		//This is the new, non-deprecated way of getting text from the Clipboard.
		//CharSequence clipTxt = clipboard.getPrimaryClip().getItemAt(0).getText();
        
        
        //If the clipboard has text, and it is more than 0 characters.
        if((null != clipTxt) && (clipTxt.length() > 0)){
        	try {
        		qrTxt.setText(clipTxt);
        		copiedStr = clipTxt.toString();
				fullUrl += URLEncoder.encode(copiedStr, "UTF-8");
				imgLoader.displayImage(fullUrl, qrImg);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else{ //If no text display a dialog.
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	
        	builder.setTitle("QRMaker")
        	.setCancelable(true)
        	.setMessage("There was no data in the clipboard! Go copy something and come back")
        	.setIcon(R.drawable.nuke)
        	.setNeutralButton("Okay", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					finish();
					
				}

        	});
        	
        	AlertDialog diag = builder.create();
        	diag.show();
        }
    } 
}
