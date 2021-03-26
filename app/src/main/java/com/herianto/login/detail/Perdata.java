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

public class Perdata extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ImageButton btn_back_perdata;

    String idpemakai, username, fullname, jabatan;

    SharedPreferences sharedpreferences;
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdata);

        webView = (WebView)findViewById(R.id.wb_webview_perdata);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ecourt.mahkamahagung.go.id/");

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences , Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);

        btn_back_perdata = (ImageButton) findViewById(R.id.btn_back_perdata);

        btn_back_perdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perdata.this, MainActivity.class);
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