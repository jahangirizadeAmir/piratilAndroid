package com.example.ms.piratilapp.Gamer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ms.piratilapp.NewActivity.Main2Activity;
import com.example.ms.piratilapp.R;

public class RaceFragment extends Fragment {

//    TextView txt_dimond_count;
//    Button btn_start_race;
//    TextView txt_name_family, txt_rancking, txt_game_name, txt_incoming_diamond;

    TextView txt_name_family_main_page, txt_ranking_main_page;
    ImageView img_week_challange, img_dimond_finder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main3,container,false);

        txt_name_family_main_page=(TextView)view.findViewById(R.id.txt_name_family_main_page);
        txt_ranking_main_page=(TextView)view.findViewById(R.id.txt_ranking_main_page);
        img_week_challange=(ImageView)view.findViewById(R.id.img_week_challange);
        img_dimond_finder=(ImageView)view.findViewById(R.id.img_dimond_finder);

        img_week_challange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });

        img_dimond_finder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });

//        txt_dimond_count=(TextView)view.findViewById(R.id.txt_dimond_count);
//        txt_name_family =(TextView)view.findViewById(R.id.txt_name_family);
//        txt_rancking =(TextView)view.findViewById(R.id.txt_rancking);
//        txt_game_name =(TextView)view.findViewById(R.id.txt_game_name);
//        txt_incoming_diamond =(TextView)view.findViewById(R.id.txt_incoming_diamond);
//        txt_name_family.setText("Alireza Nouri");
//        txt_rancking.setText("رتبه: ۱۰۰");
//        txt_game_name.setText("بولینگ");
//        txt_incoming_diamond.setText("ورودی: ۱۰ الماس");
//        btn_start_race=(Button) view.findViewById(R.id.btn_start_race);

//        btn_start_race.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//            }
//        });

        return view;
    }
}
