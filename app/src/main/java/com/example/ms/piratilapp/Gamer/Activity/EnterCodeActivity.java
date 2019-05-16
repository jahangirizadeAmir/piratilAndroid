package com.example.ms.piratilapp.Gamer.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ms.piratilapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.example.ms.piratilapp.Class.*;
import com.example.ms.piratilapp.ServerCallback;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterCodeActivity extends AppCompatActivity {

    Button btn_code_registration;
    EditText edt_enter_code;
    TextView txt_resend_code, txt_counter;
    RequestQueue requestQueue;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);


        requestQueue = Volley.newRequestQueue(EnterCodeActivity.this);
        final Intent intent = getIntent();
        Boolean submit = intent.getBooleanExtra("submit", true);
        Boolean dataComplete = intent.getBooleanExtra("dataComplete", false);
        final String mobile = intent.getStringExtra("mobile");
        final String type = intent.getStringExtra("type");

        defineObjects();

        btn_code_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_enter_code.getText().toString().equals("")) {
                    Toast.makeText(EnterCodeActivity.this, "کد به درستی وارد نشده است!", Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("mobile", mobile);
                    stringStringHashMap.put("type", type);
                    stringStringHashMap.put("code", edt_enter_code.getText().toString().trim());

                    final ProgressDialog progressDialog = new ProgressDialog(EnterCodeActivity.this);
                    progressDialog.setMessage("لطفا منتظر باشید...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    vollayRequest vollayRequest = new vollayRequest();
                    vollayRequest.requester(stringStringHashMap, EnterCodeActivity.this, "checkCode.php", new ServerCallback() {
                        @Override
                        public void onSuccess(JSONObject result) {
                            try {
                                progressDialog.dismiss();
                                if (result.getBoolean("error")) {
                                    customToast customToast = new customToast(
                                            getApplicationContext(),
                                            result.getString("MSG"),
                                            com.example.ms.piratilapp.Class.customToast.danger,
                                            com.example.ms.piratilapp.Class.customToast.Bottom
                                    );
                                    customToast.getToast().show();
                                } else {
                                    SharedPreferences sharedPreferences = getSharedPreferences("Token", MODE_PRIVATE);
                                    sharedPreferences.edit().putString("mobile",mobile).apply();
                                    sharedPreferences.edit().putString("token",result.getString("token")).apply();
                                    Intent intent1 = new Intent(EnterCodeActivity.this,MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    txt_resend_code.setEnabled(false);
                    txt_resend_code.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(EnterCodeActivity.this, "!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        countDownTimer = new CountDownTimer(60000, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                txt_counter.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                txt_resend_code.setTextColor(getResources().getColor(R.color.link_color_enable));
                txt_resend_code.setEnabled(true);
                txt_resend_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countDownTimer.start();
                        txt_resend_code.setTextColor(getResources().getColor(R.color.link_color_disable));
                        final ProgressDialog progressDialog = new ProgressDialog(EnterCodeActivity.this);
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
                                            if (!jsonObject.getBoolean("version")) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(EnterCodeActivity.this);
                                                builder.setTitle("خطایی پیش آمده");
                                                builder.setMessage("نسخه جدید را دانلود کنید");
                                                builder.setCancelable(false);
                                                builder.show();
                                            } else {

                                                customToast customToast = new customToast(getApplicationContext(), "ارسال مجدد کد با موفقیت انجام شد", com.example.ms.piratilapp.Class.customToast.succ, com.example.ms.piratilapp.Class.customToast.Top);
                                                customToast.getToast().show();

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
                        ) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> stringStringHashMap = new HashMap<>();
                                stringStringHashMap.put("appVersion", "1");
                                stringStringHashMap.put("device", "android");
                                stringStringHashMap.put("mobile", mobile);
                                return stringStringHashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
//
//                    Intent intent = new Intent(LoginActivity.this, EnterCodeActivity.class);
//                    startActivity(intent);


                    }
                });
            }
        };
        countDownTimer.start();

    }

    private void defineObjects() {
        btn_code_registration=(Button)findViewById(R.id.btn_code_registration);
        edt_enter_code=(EditText) findViewById(R.id.edt_enter_code);
        txt_resend_code=(TextView) findViewById(R.id.txt_resend_code);
        txt_counter=(TextView) findViewById(R.id.txt_counter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}