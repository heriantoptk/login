package com.herianto.login.detail;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.herianto.login.AppController;
import com.herianto.login.Login;
import com.herianto.login.MainActivity;
import com.herianto.login.R;
import com.herianto.login.Server;
import com.herianto.login.operator.UbahPassUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UbahPassword extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText txt_password_ubah_pass_main, txt_passwordx_ubah_pass_main;
    TextView txt_id, txt_username, txt_fullname;
    Button btn_submit_ubah_pass_main;
    ImageButton btn_back_ubah_pass_main;
    String idpemakai, username, fullname, jabatan, idregister;
    SharedPreferences sharedpreferences;
    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "ubhpass.php";
    private static final String TAG = UbahPassUser.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PEMAKAI = "idpemakai";
    public final static String TAG_FULLNAME = "fullname";
    public final static String TAG_JABATAN = "jabatan";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        txt_id = (TextView) findViewById(R.id.txt_id_admin);
        txt_username = (TextView) findViewById(R.id.edt_email_ubah_pass_main);
        txt_fullname = (TextView) findViewById(R.id.edt_fullname_ubah_pass_main);
        btn_back_ubah_pass_main = (ImageButton) findViewById(R.id.btn_back_ubah_pass_main);

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

        idregister = idpemakai;
        txt_password_ubah_pass_main = (EditText) findViewById(R.id.edt_password_ubah_pass_main);
        txt_passwordx_ubah_pass_main = (EditText) findViewById(R.id.edt_passwordx_ubah_pass_main);
        btn_submit_ubah_pass_main = (Button) findViewById(R.id.btn_submit_ubah_pass_main);



        txt_username.setText(username);
        txt_fullname.setText(fullname);

        btn_back_ubah_pass_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UbahPassword.this, MainActivity.class);
                intent.putExtra(TAG_PEMAKAI, idpemakai);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_FULLNAME, fullname);
                intent.putExtra(TAG_JABATAN, jabatan);
                finish();
                startActivity(intent);
            }
        });

        btn_submit_ubah_pass_main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String idpemakai = idregister;
                String password = txt_password_ubah_pass_main.getText().toString();
                String confirm_password = txt_passwordx_ubah_pass_main.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    ChkPassword(idpemakai, password, confirm_password);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void ChkPassword(final String idpemakai, final String password, final String confirm_password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Updating ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Update Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_password_ubah_pass_main.setText("");
                        txt_passwordx_ubah_pass_main.setText("");


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
                Log.e(TAG, "Update Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idpemakai",idpemakai);
                params.put("password", password);
                params.put("confirm_password", confirm_password);

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

}