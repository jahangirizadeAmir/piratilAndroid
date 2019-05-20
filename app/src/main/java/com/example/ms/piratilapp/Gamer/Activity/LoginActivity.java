package com.example.ms.piratilapp.Gamer.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ms.piratilapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.example.ms.piratilapp.Class.*;
import com.example.ms.piratilapp.ServerCallback;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText edt_phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gamer);


        defineObjects();



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userPhoneNumber = edt_phone_number.getText().toString();
                if(userPhoneNumber.equals("") || userPhoneNumber.length() < 11 || userPhoneNumber.length() > 11){
                    customToast customToast = new customToast(getApplicationContext(), "شماره موبایل به درستی وارد نشده است" , com.example.ms.piratilapp.Class.customToast.info, com.example.ms.piratilapp.Class.customToast.Bottom);
                    customToast.getToast().show();
                }
                else {

                    HashMap<String,String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("mobile",edt_phone_number.getText().toString().trim());
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("لطفا منتظر باشید...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();


                    vollayRequest vollayRequest = new vollayRequest();
                       vollayRequest.requester(stringStringHashMap, LoginActivity.this, "submitUser.php", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) {
                            progressDialog.dismiss();
                            try {
                                    Intent intent = new Intent(LoginActivity.this,EnterCodeActivity.class);
                                    intent.putExtra("submit",result.getBoolean("submit"));
                                    intent.putExtra("mobile",edt_phone_number.getText().toString().trim());
                                    intent.putExtra("dataComplete",result.getBoolean("dataComplete"));
                                    intent.putExtra("type",result.getString("type"));
                                    startActivity(intent);
                                    finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
//
//                    Intent intent = new Intent(LoginActivity.this, EnterCodeActivity.class);
//                    startActivity(intent);
                }
            }
        });

    }

    private void defineObjects() {

        btn_login=(Button)findViewById(R.id.btn_login);
        edt_phone_number=(EditText) findViewById(R.id.edt_phone_number);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
