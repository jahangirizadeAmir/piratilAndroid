package com.example.ms.piratilapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ms.piratilapp.Gamer.Activity.LoginActivity;

public class ChooseAcountActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_marketer_login, btn_business_man_login, btn_gamer_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_acount);

        defineObjects();

    }

    private void defineObjects() {

        btn_marketer_login=(Button)findViewById(R.id.btn_marketer_login);
        btn_marketer_login.setOnClickListener(this);
        btn_business_man_login=(Button)findViewById(R.id.btn_business_man_login);
        btn_business_man_login.setOnClickListener(this);
        btn_gamer_login=(Button)findViewById(R.id.btn_gamer_login);
        btn_gamer_login.setOnClickListener(this);

    }



    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btn_marketer_login:
//                Intent intent = new Intent(ChooseAcountActivity.this, .class);
//                startActivity(intent);
                Toast.makeText(this, "btn_marketer_login", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_business_man_login:
//                Intent intent2 = new Intent(ChooseAcountActivity.this, .class);
//                startActivity(intent2);
                Toast.makeText(this, "btn_business_man_login", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_gamer_login:
                Intent intent3 = new Intent(ChooseAcountActivity.this, LoginActivity.class);
                startActivity(intent3);
                break;
        }
    }

}
