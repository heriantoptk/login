package com.herianto.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.herianto.login.detail.Perdata;
import com.herianto.login.detail.Pidana;
import com.herianto.login.detail.UbahPassword;
import com.herianto.login.detail.Umum;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_perdata, btn_pidana, btn_umum;
    TextView txt_id, txt_username, txt_notip;
    String idpemakai, username, fullname, jabatan, pesan;

    SharedPreferences sharedpreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";
    public final static String TAG_PESAN = "pesan";

    //public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView) findViewById(R.id.txt_id_main);
        txt_username = (TextView) findViewById(R.id.txt_username_main);
        btn_perdata = (ImageButton) findViewById(R.id.btn_perdata);
        btn_pidana = (ImageButton) findViewById(R.id.btn_pidana);
        btn_umum = (ImageButton) findViewById(R.id.btn_umum);
        txt_notip = (TextView) findViewById(R.id.txt_notip);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);
        pesan = getIntent().getStringExtra(TAG_PESAN);

        if (username != null) {
            txt_username.setText("Selamat Datang " + fullname);
            if (pesan.equals("0")) {
                txt_notip.setVisibility(View.INVISIBLE);
            } else {
                txt_notip.setText(pesan);
            }
        } else {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(MainActivity.this, Login.class);
            finish();
            startActivity(intent);
        }


        btn_perdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Perdata.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                startActivity(intent);
            }
        });

        btn_pidana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pidana.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                startActivity(intent);
            }
        });

        btn_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Umum.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.ubah_pass) {
            Intent intent = new Intent(MainActivity.this, UbahPassword.class);
            intent.putExtra(TAG_PEMAKAI, idpemakai);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_FULLNAME, fullname);
            intent.putExtra(TAG_JABATAN, jabatan);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}