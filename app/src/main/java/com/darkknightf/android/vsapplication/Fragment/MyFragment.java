package com.darkknightf.android.vsapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darkknightf.android.vsapplication.Activity.LocationActivity;
import com.darkknightf.android.vsapplication.Activity.LoginActivity;
import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.Activity.ShareActivity;
import com.darkknightf.android.vsapplication.Activity.TextActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        TextView textView1 = (TextView) view.findViewById(R.id.btn_my_address);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        TextView textView2 = (TextView) view.findViewById(R.id.btn_my_evaluates);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ShareActivity.class );
                startActivity(intent);
            }
        });
        TextView textView3 = (TextView) view.findViewById(R.id.btn_my_favorites);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TextActivity.class);
                startActivity(intent);
            }
        });
        TextView textView4 = (TextView) view.findViewById(R.id.btn_address);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

}
