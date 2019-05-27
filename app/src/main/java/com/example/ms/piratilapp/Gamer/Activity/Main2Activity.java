package com.example.ms.piratilapp.Gamer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ms.piratilapp.R;

public class Main2Activity extends AppCompatActivity {

    //    TextView txt_dimond_count;
//    Button btn_start_race;
    TextView txt_name_family, txt_rancking, txt_game_name, txt_incoming_diamond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_a);

        txt_name_family =findViewById(R.id.txt_name_family);
        txt_rancking =findViewById(R.id.txt_rancking);
        txt_game_name =findViewById(R.id.txt_game_name);
        txt_incoming_diamond =findViewById(R.id.txt_incoming_diamond);
        txt_name_family.setText("Alireza Nouri");
        txt_rancking.setText("رتبه: ۱۰۰");
        txt_game_name.setText("بولینگ");
        txt_incoming_diamond.setText("ورودی: ۱۰ الماس");

    }
}
