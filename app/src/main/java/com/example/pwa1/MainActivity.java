package com.example.pwa1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView =(WebView)findViewById( R.id.webview);
        webView.setWebViewClient( new WebViewClient());
        String url= getString(R.string.load_url);
        webView.loadUrl(url);
       WebSettings webSettings =webView.getSettings();
       webSettings.setJavaScriptEnabled(true);
       webSettings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, android.webkit.GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false); }
        });
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView,true);

      //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("MyApplication", consoleMessage.message() + " -- From line " +
                        consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();

        }
        else {
            super.onBackPressed();
        }

    }

}