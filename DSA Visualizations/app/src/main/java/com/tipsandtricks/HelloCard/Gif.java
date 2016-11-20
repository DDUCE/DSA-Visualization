package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

public class Gif extends Activity {
    WebView webviewActionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputStream stream = null;
        try {
            stream = getAssets().open("a.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        webviewActionView = (WebView) findViewById(R.id.webviewActionView);
        webviewActionView.setWebViewClient(new MyWebViewClient());
        webviewActionView.getSettings().setJavaScriptEnabled(true);

        // GifWebView view = new GifWebView(this, stream);
        // webviewActionView.addView(view);

    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
