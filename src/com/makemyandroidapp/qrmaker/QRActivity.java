package com.makemyandroidapp.qrmaker;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        CharSequence clipTxt = clipboard.getText();
        
        
        
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
        	
        }else{
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_qr, menu);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem itm){
    	if(itm.getItemId() == R.id.menu_settings){
    		clipboard.setText("");
    		
    	}
    	return true;
    }

    
}
