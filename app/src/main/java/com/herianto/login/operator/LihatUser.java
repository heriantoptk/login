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
import android.widget.TextView;

import com.herianto.login.Admin;
import com.herianto.login.Login;
import com.herianto.login.R;
import com.herianto.login.Server;

import androidx.appcompat.app.AppCompatActivity;

public class LihatUser extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    WebView webView;
    WebSettings webSettings;
    ImageButton btn_back_lihat_user;
    TextView txt_pilihan;

    String idpemakai, username, fullname, jabatan, pilihan;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";
    public final static String TAG_PILIHAN = "pilihan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_user);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences , Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);
        pilihan = getIntent().getStringExtra(TAG_PILIHAN);

        txt_pilihan = (TextView) findViewById(R.id.tv_text_lihat_user);

        if (pilihan.equals("1")) {
            txt_pilihan.setText("Daftar Hakim");
        } else {
            txt_pilihan.setText("List Panitera");
        }
        webView = (WebView)findViewById(R.id.wb_webview_lht_user);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        String url = Server.URL + "lstuser.php?aksi=lihat&jabatan="+pilihan;
        webView.loadUrl(url);

        btn_back_lihat_user = (ImageButton) findViewById(R.id.btn_back_lihat_user);

        btn_back_lihat_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatUser.this, Admin.class);
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