package com.herianto.login.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.herianto.login.MainActivity;
import com.herianto.login.R;

import static com.herianto.login.Login.my_shared_preferences;

public class Umum extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    ImageButton btn_back_umum;
    String id, username, pesan;

    public static final String TG_ID = "id";
    public static final String TG_USERNAME = "username";
    public static final String TG_FULLNAME = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umum);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id = getIntent().getStringExtra(TG_ID);
        username = getIntent().getStringExtra(TG_USERNAME);
        pesan = getIntent().getStringExtra(TG_FULLNAME);

        btn_back_umum = (ImageButton) findViewById(R.id.btn_back_umum);

        btn_back_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Umum.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}