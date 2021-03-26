package com.herianto.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.herianto.login.detail.Perdata;
import com.herianto.login.detail.Pidana;
import com.herianto.login.detail.Umum;

import static com.herianto.login.Login.my_shared_preferences;
import static com.herianto.login.Login.session_status;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_logout, btn_perdata, btn_pidana, btn_umum;
    TextView txt_id, txt_username;
    String idpemakai, username, pesan, sukses;

    SharedPreferences sharedpreferences;

    public static final String TAG_PEMAKAI = "idpemakai";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_FULLNAME = "message";
    //public static final String session_status = "session_status";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        btn_logout = (ImageButton) findViewById(R.id.btn_logout);
        btn_perdata = (ImageButton) findViewById(R.id.btn_perdata);
        btn_pidana = (ImageButton) findViewById(R.id.btn_pidana);
        btn_umum = (ImageButton) findViewById(R.id.btn_umum);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        pesan = getIntent().getStringExtra(TAG_FULLNAME);
        sukses = getIntent().getStringExtra(session_status);
        if ( username != null ) {
            txt_id.setText("USERNAME : " + sukses);
            txt_username.setText("" + pesan);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

        btn_perdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Perdata.class);
                finish();
                startActivity(intent);
            }
        });

        btn_pidana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pidana.class);
                finish();
                startActivity(intent);
            }
        });

        btn_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Umum.class);
                finish();
                startActivity(intent);
            }
        });

    }
}