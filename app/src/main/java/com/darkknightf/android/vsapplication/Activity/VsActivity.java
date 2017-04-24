package com.darkknightf.android.vsapplication.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darkknightf.android.vsapplication.Fragment.BlankFragment;
import com.darkknightf.android.vsapplication.Fragment.MyFragment;
import com.darkknightf.android.vsapplication.Fragment.OrderFragment;
import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class VsActivity extends AppCompatActivity implements View.OnClickListener{
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ViewPager viewPager;
    private LinearLayout Index,Orders,My;
    private ImageView ivIndex,ivOrders,ivMy,ivCurrent;
    private TextView tvIndex,tvOrders,tvMy,tvCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        initView();
        initData();
        Bmob.initialize(this, "557fcf85cd570d652340620a0d62e5bf");

    }
    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        Index = (LinearLayout) findViewById(R.id.index);
        Orders = (LinearLayout) findViewById(R.id.orders);
        My = (LinearLayout) findViewById(R.id.my);

        Index.setOnClickListener(this);
        Orders.setOnClickListener(this);
        My.setOnClickListener(this);

        ivIndex=(ImageView)findViewById(R.id.iv_index);
        ivOrders= (ImageView) findViewById(R.id.iv_orders);
        ivMy= (ImageView) findViewById(R.id.iv_my);

        tvIndex= (TextView) findViewById(R.id.tv_index);
        tvOrders= (TextView) findViewById(R.id.tv_orders);
        tvMy= (TextView) findViewById(R.id.tv_my);


        ivIndex.setSelected(true);
        tvIndex.setSelected(true);
        ivCurrent = ivIndex;
        tvCurrent = tvIndex;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(2);
    }

    private void initData(){

        Fragment blankFragment = new BlankFragment();
        Fragment orderFragment = new OrderFragment();
        Fragment myFragment = new MyFragment();

        fragments.add(blankFragment);
        fragments.add(orderFragment);
        fragments.add(myFragment);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

    private void changeTab(int id){
        ivCurrent.setSelected(false);
        tvCurrent.setSelected(false);
        switch (id){
            case R.id.index:
                viewPager.setCurrentItem(0);
            case 0:
                ivIndex.setSelected(true);
                ivCurrent = ivIndex;
                tvIndex.setSelected(true);
                tvCurrent = tvIndex;
                break;
            case R.id.orders:
                viewPager.setCurrentItem(1);
            case 1:
                ivOrders.setSelected(true);
                ivCurrent = ivOrders;
                tvOrders.setSelected(true);
                tvCurrent = tvOrders;
                break;
            case R.id.my:
                viewPager.setCurrentItem(2);
            case 2:
                ivMy.setSelected(true);
                ivCurrent = ivMy;
                tvMy.setSelected(true);
                tvCurrent = tvMy;
                break;
            default:
                break;
        }
    }
}
