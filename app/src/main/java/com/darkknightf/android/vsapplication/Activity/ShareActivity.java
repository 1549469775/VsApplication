package com.darkknightf.android.vsapplication.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.darkknightf.android.vsapplication.Activity.AddActivity;
import com.darkknightf.android.vsapplication.BaseActivity;
import com.darkknightf.android.vsapplication.Constants;
import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.adapter.BaseAdapterHelper;
import com.darkknightf.android.vsapplication.adapter.QuickAdapter;
import com.darkknightf.android.vsapplication.bean.Share;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import static com.darkknightf.android.vsapplication.R.id.tv_describe;


public class ShareActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener {

    RelativeLayout layout_action;//
    LinearLayout layout_all;
    TextView tv_lost;
    ListView listview;
    Button btn_add;

    protected QuickAdapter<Share> ShareAdapter;

    RelativeLayout progress;
    LinearLayout layout_no;
    TextView tv_no;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_share);
    }

    @Override
    public void initViews() {

        progress = (RelativeLayout) findViewById(R.id.progress);
        layout_no = (LinearLayout) findViewById(R.id.layout_no);
        tv_no = (TextView) findViewById(R.id.tv_no);

        layout_action = (RelativeLayout) findViewById(R.id.layout_action);
        layout_all = (LinearLayout) findViewById(R.id.layout_all);
        tv_lost = (TextView) findViewById(R.id.tv_lost);

        tv_lost.setTag("Share");
        listview = (ListView) findViewById(R.id.list_lost1);


        //listview.setEmptyView(findViewById(R.id.empty_imageview_iv));
        btn_add = (Button) findViewById(R.id.btn_add);

    }

    @Override
    public void initListeners() {
       listview.setOnItemLongClickListener(this);
        btn_add.setOnClickListener(this);
        layout_all.setOnClickListener(this);
    }

    @Override
    public void initData() {

        if (ShareAdapter == null) {
            ShareAdapter = new QuickAdapter<Share>(this, R.layout.item_list) {
                @Override
                protected void convert(BaseAdapterHelper helper, Share share) {
                    helper
                            //.setText(tv_title,share.getUsername())
                            .setText(tv_describe, share.getDescribe());

                }
            };
           listview.setAdapter(ShareAdapter);
            // 默认加载失物界面
            queryLosts();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == layout_all) {
            //showListPop();
        } else if (v == btn_add) {
            Intent intent = new Intent(this, AddActivity.class);
            intent.putExtra("from", tv_lost.getTag().toString());
            startActivityForResult(intent, Constants.REQUESTCODE_ADD);
        } //else if (v == layout_found) {
            //changeTextView(v);
            //morePop.dismiss();
            //queryFounds();
        //}
    //else if (v == layout_lost) {
      //      changeTextView(v);
        //    morePop.dismiss();
          //  queryLosts();
        //}
    }

   
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
    private void queryLosts() {
        showView();
        BmobQuery<Share> query = new BmobQuery<Share>();
        query.order("-createdAt");// 按照时间降序
        query.findObjects(this, new FindListener<Share>() {
            @Override
            public void onSuccess(List<Share> list) {
                ShareAdapter.clear();

                if (list == null || list.size() == 0) {
                    showErrorView(1);
                    ShareAdapter.notifyDataSetChanged();
                    return;
                }
                ShareAdapter.addAll(list);
                listview.setAdapter(ShareAdapter);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onError(int i, String s) {
                showErrorView(0);
            }
        });}

    private void showErrorView(int tag) {
        progress.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        layout_no.setVisibility(View.VISIBLE);
        if (tag == 0) {
            tv_no.setText(getResources().getText(R.string.list_no_data_share));
        } else {
           // tv_no.setText(getResources().getText(R.string.list_no_data_found));
        }
    }

    private void showView() {
       listview.setVisibility(View.VISIBLE);
        layout_no.setVisibility(View.GONE);
    }



}
