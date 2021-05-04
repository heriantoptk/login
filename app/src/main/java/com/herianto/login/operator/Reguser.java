package com.herianto.login.operator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.herianto.login.Admin;
import com.herianto.login.AppController;
import com.herianto.login.Login;
import com.herianto.login.R;
import com.herianto.login.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Reguser extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    ProgressDialog pDialog;
    Button btn_submit_reguser;
    ImageButton btn_back_reguser;
    EditText txt_email_reguser, txt_password_reguser, txt_fullname_reguser, txt_passwordx_reguser;
    RadioButton rButton_admin, rButton_hakim;

    Intent intent;
    int success;
    ConnectivityManager conMgr;
    private String url = Server.URL + "register.php";
    private static final String TAG = Reguser.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String idpemakai, username, fullname, jabatan, pangkat;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";

    String tag_json_obj = "json_obj_req";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reguser);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences , Context.MODE_PRIVATE);
        idpemakai = getIntent().getStringExtra(TAG_PEMAKAI);
        username = getIntent().getStringExtra(TAG_USERNAME);
        fullname = getIntent().getStringExtra(TAG_FULLNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);

        btn_back_reguser = (ImageButton) findViewById(R.id.btn_back_reguser);
        btn_submit_reguser = (Button) findViewById(R.id.btn_submit_reguser);

        txt_email_reguser = (EditText) findViewById(R.id.edt_email_reguser);
        txt_password_reguser = (EditText) findViewById(R.id.edt_password_reguser);
        txt_passwordx_reguser = (EditText) findViewById(R.id.edt_passwordx_reguser);
        txt_fullname_reguser = (EditText) findViewById(R.id.edt_fullname_reguser);
        rButton_admin = (RadioButton) findViewById(R.id.radioButton_admin);
        rButton_hakim = (RadioButton) findViewById(R.id.radioButton_hakim);

        btn_back_reguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Reguser.this, Admin.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                finish();
                startActivity(intent);
            }
        });

        btn_submit_reguser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String fullname = txt_fullname_reguser.getText().toString();
                String username = txt_email_reguser.getText().toString();
                String password = txt_password_reguser.getText().toString();
                String confirm_password = txt_passwordx_reguser.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister(fullname, username, password, confirm_password, pangkat);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkRegister(final String fullname, final String username, final String password, final String confirm_password, final String pangkat) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        txt_fullname_reguser.setText("");
                        txt_email_reguser.setText("");
                        txt_password_reguser.setText("");
                        txt_passwordx_reguser.setText("");


                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("fullname", fullname);
                params.put("username", username);
                params.put("password", password);
                params.put("confirm_password", confirm_password);
                params.put("pangkat",pangkat);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButton_admin:
                if (checked)
                    pangkat = "0";
                    break;

            case R.id.radioButton_hakim:
                if (checked)
                    pangkat = "1";
                    break;

            case R.id.radioButton_panitera:
                if (checked)
                    pangkat = "2";
                    break;
        }
    }
}