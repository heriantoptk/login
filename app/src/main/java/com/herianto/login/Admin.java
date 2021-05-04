package com.herianto.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.herianto.login.operator.EntrySidang;
import com.herianto.login.operator.LihatAgenda;
import com.herianto.login.operator.LihatUser;
import com.herianto.login.operator.Reguser;
import com.herianto.login.operator.UbahPassUser;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    ImageButton btn_logoutx, btn_admin_user, btn_ubah_pass, btn_admin_sidang ;
    Button button;
    TextView txt_id, txt_username;
    String idpemakai, username, fullname, jabatan ;

    SharedPreferences sharedpreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";
    public final static String TAG_PILIHAN = "pilihan";

    //public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txt_id = (TextView) findViewById(R.id.txt_id_admin);
        txt_username = (TextView) findViewById(R.id.txt_username_admin);
        btn_logoutx = (ImageButton) findViewById(R.id.btn_logoutx);
        btn_admin_user = (ImageButton) findViewById(R.id.btn_admin_user);
        btn_ubah_pass = (ImageButton) findViewById(R.id.btn_ubah_pass);
        btn_admin_sidang = (ImageButton) findViewById(R.id.btn_admin_sidang);

//       Ini utk ubah gambar btn_admin_user.setImageResource(R.drawable.ic_power);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);

        if (username != null) {
            txt_username.setText("Selamat Datang " + fullname);
        } else {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(Admin.this, Login.class);
            finish();
            startActivity(intent);
        }

        btn_logoutx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(Admin.this, Login.class);
                startActivity(intent);
            }
        });


        btn_admin_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Reguser.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                startActivity(intent);
            }
        });

        btn_ubah_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, UbahPassUser.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                startActivity(intent);
            }
        });

        btn_admin_sidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, EntrySidang.class);
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
        getMenuInflater().inflate(R.menu.menuuser, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_user) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(Admin.this, Login.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.list_user) {
            Intent intent = new Intent(Admin.this, LihatUser.class);
            intent.putExtra(TAG_PEMAKAI, idpemakai);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_FULLNAME, fullname);
            intent.putExtra(TAG_JABATAN, jabatan);
            intent.putExtra(TAG_PILIHAN,"1");
            startActivity(intent);
            return true;
        } else if (id == R.id.list_userganti) {
            Intent intent = new Intent(Admin.this, LihatUser.class);
            intent.putExtra(TAG_PEMAKAI, idpemakai);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_FULLNAME, fullname);
            intent.putExtra(TAG_JABATAN, jabatan);
            intent.putExtra(TAG_PILIHAN,"2");
            startActivity(intent);
            return true;
        } else if (id == R.id.list_agenda) {
            Intent intent = new Intent(Admin.this, LihatAgenda.class);
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