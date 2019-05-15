package com.example.ms.piratilapp.Gamer.Activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ms.piratilapp.Gamer.Fragment.DimondFinderFragment;
import com.example.ms.piratilapp.Gamer.Fragment.ProfileFragment;
import com.example.ms.piratilapp.Gamer.Fragment.RaceFragment;
import com.example.ms.piratilapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    LinearLayout lyt_dimond_finder,lyt_race, lyt_profile;
    ImageView img_dimond_finder,img_race, img_profile;
    TextView txt_dimond_finder,txt_race, txt_profile;
    ColorStateList defaultTextviewColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        definObjects();

        defaultTextviewColor = txt_dimond_finder.getTextColors();

        lyt_dimond_finder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_race.setTextColor(defaultTextviewColor);
                txt_dimond_finder.setTextColor(getResources().getColor(R.color.bgToolbar));
                txt_profile.setTextColor(defaultTextviewColor);
                DimondFinderFragment dimondFinderFragment = new DimondFinderFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmenttest,dimondFinderFragment).commit();
            }
        });

        lyt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_race.setTextColor(defaultTextviewColor);
                txt_dimond_finder.setTextColor(defaultTextviewColor);
                txt_profile.setTextColor(getResources().getColor(R.color.bgToolbar));
                ProfileFragment profileFragment = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmenttest,profileFragment).commit();

            }
        });

        lyt_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_race.setTextColor(getResources().getColor(R.color.bgToolbar));
                txt_dimond_finder.setTextColor(defaultTextviewColor);
                txt_profile.setTextColor(defaultTextviewColor);
                RaceFragment raceFragment = new RaceFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmenttest,raceFragment).commit();
            }
        });

    }

    private void definObjects() {

        lyt_dimond_finder=(LinearLayout)findViewById(R.id.lyt_dimond_finder);
        lyt_race=(LinearLayout)findViewById(R.id.lyt_race);
        lyt_profile=(LinearLayout)findViewById(R.id.lyt_profile);

        img_dimond_finder=(ImageView)findViewById(R.id.img_dimond_finder);
        img_race=(ImageView)findViewById(R.id.img_race);
        img_profile=(ImageView)findViewById(R.id.img_profile);

        txt_dimond_finder=(TextView)findViewById(R.id.txt_dimond_finder);
        txt_race=(TextView)findViewById(R.id.txt_race);
        txt_profile=(TextView)findViewById(R.id.txt_profile);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
