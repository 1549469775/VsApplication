package com.darkknightf.android.vsapplication.Activity;

import android.content.Context;
import android.location.Location;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MarkerOptions;
import com.darkknightf.android.vsapplication.Fragment.MapFragment;
import com.darkknightf.android.vsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private MapFragment fragment_world;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        fragmentManager = getSupportFragmentManager();
        if (fragment_world == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragment_world = (MapFragment) MapFragment.newInstance();
            fragmentTransaction.add(R.id.content11,fragment_world);
            fragmentTransaction.commit();
        }
    }

}
