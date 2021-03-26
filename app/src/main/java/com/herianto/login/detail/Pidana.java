package com.herianto.login.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.herianto.login.MainActivity;
import com.herianto.login.R;

public class Pidana extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ImageButton btn_back_pidana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pidana);

        webView = (WebView)findViewById(R.id.wb_webview_pidana);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://missyelink.pn-singkawang.go.id/");

        btn_back_pidana = (ImageButton) findViewById(R.id.btn_back_pidana);

        btn_back_pidana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pidana.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}