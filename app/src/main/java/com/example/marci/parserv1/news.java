package com.example.marci.parserv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

public class news extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String link = intent.getStringExtra(ViewMain.FILTER);

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl(link);
        if(1=2){}


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
