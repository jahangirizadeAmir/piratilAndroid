package com.example.ms.piratilapp.NewActivity;

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

import com.example.ms.piratilapp.R;

public class ChallengeFragment extends Fragment {

    TextView txt_game_name, txt_incoming_diamond, txt_name_family, txt_rancking;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.race_a,container,false);











        txt_game_name=(TextView)view.findViewById(R.id.txt_game_name);
        txt_incoming_diamond=(TextView)view.findViewById(R.id.txt_incoming_diamond);
        txt_name_family=(TextView)view.findViewById(R.id.txt_name_family);
        txt_rancking=(TextView)view.findViewById(R.id.txt_rancking);

        return view;
    }
}
