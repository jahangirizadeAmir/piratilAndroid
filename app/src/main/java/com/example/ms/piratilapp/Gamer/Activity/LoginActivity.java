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


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText edt_phone_number;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gamer);


        defineObjects();

        requestQueue = Volley.newRequestQueue(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userPhoneNumber = edt_phone_number.getText().toString();
                if(userPhoneNumber.equals("") || userPhoneNumber.length() < 11 || userPhoneNumber.length() > 11){
                    customToast customToast = new customToast(getApplicationContext(), "شماره موبایل به درستی وارد نشده است" , com.example.ms.piratilapp.Class.customToast.info, com.example.ms.piratilapp.Class.customToast.Bottom);
                    customToast.getToast().show();
                }
                else {

                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("لطفا منتظر باشید...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST,
                            "http://piratil.com/game/request/submitUser.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    progressDialog.dismiss();

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if(!jsonObject.getBoolean("version")){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                            builder.setTitle("خطایی پیش آمده");
                                            builder.setMessage("نسخه جدید را دانلود کنید");
                                            builder.setCancelable(false);
                                            builder.show();
                                        }else{
                                            String type = jsonObject.getString("type");
                                            Boolean submit = jsonObject.getBoolean("submit");
                                            Intent intent = new Intent(LoginActivity.this,EnterCodeActivity.class);
                                            intent.putExtra("submit",submit);
                                            intent.putExtra("mobile",edt_phone_number.getText().toString().trim());
                                            intent.putExtra("type",type);
                                            startActivity(intent);
                                            finish();

                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> stringStringHashMap = new HashMap<>();
                            stringStringHashMap.put("appVersion","1");
                            stringStringHashMap.put("device","android");
                            stringStringHashMap.put("mobile",edt_phone_number.getText().toString().trim());
                            return  stringStringHashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
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
