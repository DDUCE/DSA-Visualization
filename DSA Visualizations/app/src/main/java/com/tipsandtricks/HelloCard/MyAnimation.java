package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MyAnimation extends AppCompatActivity{

    Bitmap bitmap;
    View view;
    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    FileOutputStream fileoutputstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        Bundle bundle = getIntent().getExtras();
        String f = bundle.getString("data_click");
        this.setTitle(f.substring(0,f.length()-5) + " Animation");

        WebView WebViewWithJavaScript = (WebView) findViewById(R.id.webView2);
        WebViewWithJavaScript.getSettings().setBuiltInZoomControls(true);
        WebViewWithJavaScript.setInitialScale(1);
        WebViewWithJavaScript.getSettings().setLoadWithOverviewMode(true);
        WebViewWithJavaScript.getSettings().setUseWideViewPort(true);
        WebViewWithJavaScript.getSettings().setSupportMultipleWindows(true);

        WebSettings webSetting = WebViewWithJavaScript.getSettings();
        webSetting.setJavaScriptEnabled(true);

        WebViewWithJavaScript.setWebViewClient(new WebViewClient());
        WebViewWithJavaScript.loadUrl("file:///android_asset/release1.1/" + f);

        // floating bt

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View OnclickView) {

                bytearrayoutputstream = new ByteArrayOutputStream();
                view = OnclickView.getRootView();

                view.setDrawingCacheEnabled(true);

                bitmap = view.getDrawingCache();


                bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);
                file = new File( Environment.getExternalStorageDirectory() + "/screenshot.png");
                try
                {
                    file.createNewFile();
                    fileoutputstream = new FileOutputStream(file);
                    fileoutputstream.write(bytearrayoutputstream.toByteArray());
                    fileoutputstream.close();

                    Toast.makeText(getApplicationContext(),"Screen Shot taken",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    public void onSaveInstanceState(Bundle savedInstanceState)
    {

        super.onSaveInstanceState(savedInstanceState);
    }
}
