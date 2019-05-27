package com.example.ms.piratilapp.Gamer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ms.piratilapp.R;

public class Main3Activity extends AppCompatActivity {

    TextView txt_name_family_main_page, txt_ranking_main_page;
    ImageView img_week_challange, img_dimond_finder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        defineObjects();

        img_week_challange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        img_dimond_finder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    private void defineObjects() {

        txt_name_family_main_page=(TextView)findViewById(R.id.txt_name_family_main_page);
        txt_ranking_main_page=(TextView)findViewById(R.id.txt_ranking_main_page);
        img_week_challange=(ImageView)findViewById(R.id.img_week_challange);
        img_dimond_finder=(ImageView)findViewById(R.id.img_dimond_finder);

    }
}
