package com.example.ms.piratilapp.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ms.piratilapp.Class.customToast;
import com.example.ms.piratilapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ComplateProfileActivity extends AppCompatActivity {

    Button btn_summbit_info;
    EditText edt_name_family, reagent_phone_number;
    RequestQueue requestQueue;
    RadioButton rbtn_woman, rbtn_men;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complate_profile);

        defineObjects();

        btn_summbit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String gender;
                if (rbtn_men.isChecked()){
                    gender="1";
                } else {
                    gender="0";
                }

                requestQueue = Volley.newRequestQueue(getApplicationContext());

                final SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                final String token = sharedPreferences.getString("token", null);
                final String mobile = sharedPreferences.getString("mobile", null);

                if (edt_name_family.getText().toString().equals("")) {
                    customToast customToast = new customToast(getApplicationContext(), "نام و نام خانوادگی وارد نشده است", com.example.ms.piratilapp.Class.customToast.danger, com.example.ms.piratilapp.Class.customToast.Top);
                    customToast.getToast().show();
                } else if (!rbtn_men.isChecked() && !rbtn_woman.isChecked()) {
                    customToast customToast = new customToast(getApplicationContext(), "جنسیت انتخاب نشده است", com.example.ms.piratilapp.Class.customToast.danger, com.example.ms.piratilapp.Class.customToast.Bottom);
                    customToast.getToast().show();
                    reagent_phone_number.setText("");
                } else if (reagent_phone_number.getText().toString().trim().equals(mobile)){
                    customToast customToast = new customToast(getApplicationContext(), "ثبت نام کننده نمی‌تواند معرف خود باشد!!", com.example.ms.piratilapp.Class.customToast.danger, com.example.ms.piratilapp.Class.customToast.Bottom);
                    reagent_phone_number.setText("");
                    customToast.getToast().show();
                } else if(!reagent_phone_number.equals("") && reagent_phone_number.length() > 11 && edt_name_family.length()<11){
                    customToast customToast = new customToast(getApplicationContext(), "شماره موبایل معرف به درستی وارد نشده است" , com.example.ms.piratilapp.Class.customToast.info, com.example.ms.piratilapp.Class.customToast.Bottom);
                    customToast.getToast().show();
                }

                else {


                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST,
                            "http://piratil.com/game/request/complateSubmit.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//                                    Toast.makeText(ComplateProfileActivity.this, response, Toast.LENGTH_SHORT).show();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if(!jsonObject.getBoolean("version")){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(ComplateProfileActivity.this);
                                            builder.setTitle("خطایی پیش آمده");
                                            builder.setMessage("نسخه جدید را دانلود کنید");
                                            builder.setCancelable(false);
                                            builder.show();
                                        }else{

                                            if (jsonObject.getBoolean("error")){
                                                customToast customToast = new customToast(getApplicationContext(), jsonObject.getString("MSG"), com.example.ms.piratilapp.Class.customToast.danger, com.example.ms.piratilapp.Class.customToast.Bottom);
                                                customToast.getToast().show();
                                            }else {
                                                SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                                                sharedPreferences.edit().putBoolean("login", true).apply();
                                                sharedPreferences.edit().putString("token", token).apply();
                                                sharedPreferences.edit().putString("mobile", mobile).apply();

                                                Intent intent = new Intent(ComplateProfileActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
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
                            stringStringHashMap.put("mobile",mobile);
                            stringStringHashMap.put("token", token+getSaltString());
                            stringStringHashMap.put("name", edt_name_family.getText().toString().trim());
                            stringStringHashMap.put("invitedMobile", reagent_phone_number.getText().toString().trim());
                            stringStringHashMap.put("gender", gender);
                            return  stringStringHashMap;
                        }

                    };

                    requestQueue.add(stringRequest);

                }

            }
        });

    }

    private void defineObjects() {

        btn_summbit_info=(Button)findViewById(R.id.btn_summbit_info);
        edt_name_family=(EditText) findViewById(R.id.edt_name_family);
        reagent_phone_number=(EditText) findViewById(R.id.reagent_phone_number);
        rbtn_men=(RadioButton) findViewById(R.id.rbtn_men);
        rbtn_woman=(RadioButton) findViewById(R.id.rbtn_woman);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
