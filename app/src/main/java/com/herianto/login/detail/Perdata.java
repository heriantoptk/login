package com.herianto.login.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.herianto.login.MainActivity;
import com.herianto.login.R;

import androidx.appcompat.app.AppCompatActivity;

public class Perdata extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ImageButton btn_back_perdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdata);

        webView = (WebView)findViewById(R.id.wb_webview_perdata);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ecourt.mahkamahagung.go.id/");

        btn_back_perdata = (ImageButton) findViewById(R.id.btn_back_perdata);

        btn_back_perdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perdata.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}