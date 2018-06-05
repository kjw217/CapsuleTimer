package com.example.knight.a2018_mobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @brief
 * @author Knight
 * @date 2018.06.03
 * @version 1.0.0.1
 */
public class AppHomepage extends AppCompatActivity {

    WebView browser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        browser = findViewById(R.id.webkit);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl("http://" + "106.10.40.50:5000");
    }

}
