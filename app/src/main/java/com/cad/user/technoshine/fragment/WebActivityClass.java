package com.cad.user.technoshine.fragment;

/**
 * Created by sony on 17-09-2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cad.user.technoshine.R;

public class WebActivityClass extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://2015.cadnitd.co.in/nethunt_1");

    }

}
