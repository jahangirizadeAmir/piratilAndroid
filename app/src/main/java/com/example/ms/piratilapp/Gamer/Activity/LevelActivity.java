package com.example.ms.piratilapp.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ms.piratilapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LevelActivity extends AppCompatActivity {

    TextView txt_level_count, txt_level_content;
    ImageView img_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        defineObjects();

        img_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(LevelActivity.this, WinnerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void defineObjects() {

        txt_level_count=(TextView)findViewById(R.id.txt_level_count);
        txt_level_content=(TextView)findViewById(R.id.txt_level_content);
        img_qrcode=(ImageView) findViewById(R.id.img_qrcode);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
