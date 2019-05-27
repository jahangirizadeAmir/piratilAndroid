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

import com.example.ms.piratilapp.NewActivity.RollsActivity;
import com.example.ms.piratilapp.R;

public class ProfileFragment extends Fragment {

    Button btn_exit, btn_transactions, btn_checkout, btn_roles;
    TextView txt_dimond_count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        btn_exit=(Button)view.findViewById(R.id.btn_exit);
        btn_transactions=(Button)view.findViewById(R.id.btn_transactions);
        btn_checkout=(Button)view.findViewById(R.id.btn_checkout);
        btn_roles=(Button)view.findViewById(R.id.btn_roles);
        txt_dimond_count=(TextView) view.findViewById(R.id.txt_dimond_count);

        btn_roles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RollsActivity.class);
                startActivity(intent);
            }
        });

        btn_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RollsActivity.class);
                startActivity(intent);
            }
        });

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RollsActivity.class);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RollsActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

}
