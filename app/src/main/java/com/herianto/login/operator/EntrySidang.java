package com.herianto.login.operator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.herianto.login.Admin;
import com.herianto.login.Login;
import com.herianto.login.R;
import com.herianto.login.Server;

import androidx.appcompat.app.AppCompatActivity;

public class EntrySidang extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    WebView webView;
    WebSettings webSettings;
    ImageButton btn_back_entrysidang;

    String idpemakai, username, fullname, jabatan, pilihan;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";
    public final static String TAG_PILIHAN = "pilihan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_sidang);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences , Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);
        pilihan = getIntent().getStringExtra(TAG_PILIHAN);

        webView = (WebView)findViewById(R.id.wb_webview_entrysidang);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        String url = Server.URL + "agenda.php";
        webView.loadUrl(url);

        btn_back_entrysidang = (ImageButton) findViewById(R.id.btn_back_entrysidang);

        btn_back_entrysidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntrySidang.this, Admin.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                finish();
                startActivity(intent);
            }
        });
    }
}