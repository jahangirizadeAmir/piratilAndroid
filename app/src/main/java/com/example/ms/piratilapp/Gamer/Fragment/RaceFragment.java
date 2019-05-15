package com.example.ms.piratilapp.Gamer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ms.piratilapp.R;

public class RaceFragment extends Fragment {

    TextView txt_dimond_count;
    Button btn_start_race;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race,container,false);
        txt_dimond_count=(TextView)view.findViewById(R.id.txt_dimond_count);
        btn_start_race=(Button) view.findViewById(R.id.btn_start_race);

        btn_start_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
            }
        });

        return view;
    }
}
