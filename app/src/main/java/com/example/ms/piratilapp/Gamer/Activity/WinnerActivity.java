package com.example.ms.piratilapp.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ms.piratilapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WinnerActivity extends AppCompatActivity {

    TextView txt_dimond_count_win;
    TextView btn_race_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        defineObjects();

        btn_race_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinnerActivity.this, MainActivity.class);
            }
        });

    }

    private void defineObjects() {

        txt_dimond_count_win=(TextView)findViewById(R.id.txt_dimond_count_win);
        btn_race_end=(Button)findViewById(R.id.btn_race_end);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
