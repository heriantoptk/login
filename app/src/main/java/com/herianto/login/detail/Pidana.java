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

import com.herianto.login.Login;
import com.herianto.login.MainActivity;
import com.herianto.login.R;

import androidx.appcompat.app.AppCompatActivity;

public class Pidana extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ImageButton btn_back_pidana;

    String idpemakai, username, fullname, jabatan, pesan;

    SharedPreferences sharedpreferences;
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";
    public final static String TAG_PESAN = "pesan";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pidana);

        webView = (WebView)findViewById(R.id.wb_webview_pidana);
        webSettings = webView.getSettings();

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences , Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);
        pesan = getIntent().getStringExtra(TAG_PESAN);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("http://missyelink.pn-singkawang.go.id/");


        btn_back_pidana = (ImageButton) findViewById(R.id.btn_back_pidana);

        btn_back_pidana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pidana.this, MainActivity.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                intent.putExtra(TAG_PESAN, pesan);
                startActivity(intent);
                finish();
            }
        });
    }
}