package com.example.aladin_bureau.kademiatn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;

import android.view.ViewGroup;
import android.view.WindowManager;

public class MenyNavigation extends AppCompatActivity {
    private TextView mTextMessage;
    Verifier verifier=new Verifier();
    private VideoEnabledWebView webView;
    private VideoEnabledWebChromeClient webChromeClient;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent hm = new Intent(MenyNavigation.this,MenyNavigation.class);
                    startActivity(hm);
                    break;
                case R.id.navigation_dashboard:

                         Intent intent = new Intent(MenyNavigation.this,qrscannerActivity.class);
                         startActivity(intent);
                          MenyNavigation.this.finish();
                             break;


                case R.id.navigation_notifications:
                      Intent it = new Intent(MenyNavigation.this,Contact.class);
                      startActivity(it);
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String chaine=getIntent().getStringExtra("qrlink");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meny_navigation);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Save the web view
        webView = (VideoEnabledWebView)findViewById(R.id.webView);
        getSupportActionBar().hide();
        // Initialize the VideoEnabledWebChromeClient and set event handlers
        View nonVideoLayout = findViewById(R.id.nonVideoLayout); // Your own view, read class comments
        ViewGroup videoLayout = (ViewGroup)findViewById(R.id.videoLayout); // Your own view, read class comments
        //noinspection all
        View loadingView = getLayoutInflater().inflate(R.layout.view_loading_video, null); // Your own view, read class comments
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                // Your code...
            }
        };
        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
        {
            @Override
            public void toggledFullscreen(boolean fullscreen)
            {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen)
                {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                }
                else
                {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }

            }
        });
        webView.setWebChromeClient(webChromeClient);
        // Call private class InsideWebViewClient
        webView.setWebViewClient(new InsideWebViewClient());

        // Navigate anywhere you want, but consider that this classes have only been tested on YouTube's mobile site
        if(chaine==null) {
            webView.loadUrl("http://rumi-app.org/dashboard.html");
        }else{webView.loadUrl(chaine);}
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getmOnNavigationItemSelectedListener() {
        return mOnNavigationItemSelectedListener;
    }

    public void setmOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener) {
        this.mOnNavigationItemSelectedListener = mOnNavigationItemSelectedListener;
    }

    private class InsideWebViewClient extends WebViewClient {
        @Override
        // Force links to be opened inside WebView and not in Default Browser
        // Thanks http://stackoverflow.com/a/33681975/1815624
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public void onBackPressed()
    {
        // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
        if (!webChromeClient.onBackPressed())
        {
            if (webView.canGoBack())
            {
                webView.goBack();
            }
            else
            {
                // Standard back button implementation (for example this could close the app)
                super.onBackPressed();
            }
        }
    }

}
