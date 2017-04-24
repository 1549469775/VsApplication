package com.darkknightf.android.vsapplication.Activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.darkknightf.android.vsapplication.BaseActivity;
import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.bean.Share;

import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends BaseActivity implements View.OnClickListener {

    EditText edit_describe;
    Button btn_back,tv_put;

    TextView tv_add;
    String from = "";


    String old_describe = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_add);
    }

    @Override
    public void initViews() {

        tv_add= (TextView) findViewById(R.id.tv_add);
        btn_back = (Button) findViewById(R.id.btn_back);
        tv_put= (Button) findViewById(R.id.text_put);
        //edit_photo = (EditText) findViewById(R.id.edit_photo);
        edit_describe = (EditText) findViewById(R.id.word_message);
        //edit_title = (EditText) findViewById(R.id.edit_title);

    }

    @Override
    public void initListeners() {

        btn_back.setOnClickListener(this);
        tv_put.setOnClickListener(this);
    }

    @Override
    public void initData() {
        from = getIntent().getStringExtra("from");
        //old_title = getIntent().getStringExtra("title");
        //old_phone = getIntent().getStringExtra("phone");
        old_describe = getIntent().getStringExtra("describe");

        //edit_title.setText(old_title);
        edit_describe.setText(old_describe);
        //edit_photo.setText(old_phone);

        if (from.equals("Share")) {
            tv_add.setText("添加share信息");
        } else {
            //tv_add.setText("添加招领信息");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tv_put) {
            addByType();
        } else if (v == btn_back) {
            finish();
        }
    }
    String title = "";
    String describe = "";
    String photo="";

    /**根据类型添加失物/招领
     * addByType
     * @Title: addByType
     * @throws
     */
    private void addByType(){
        //title = edit_title.getText().toString();
        describe = edit_describe.getText().toString();
       // photo = edit_photo.getText().toString();

       // if(TextUtils.isEmpty(title)){
           // ShowToast("请填写标题");
           // return;
        //}
        if(TextUtils.isEmpty(describe)){
            ShowToast("请填写描述");
            return;
        }
        //if(TextUtils.isEmpty(photo)){
            //ShowToast("请填写手机");
           // return;
        //}
        if(from.equals("Share")){
            addShare();
        }else{
            //addFound();
        }
    }
    private void addShare(){
        Share share = new Share();
        share.setDescribe(describe);
        //lost.setPhone(photo);
        //lost.setTitle(title);
        share.save(this, new SaveListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ShowToast("share信息添加成功!");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                ShowToast("添加失败:"+arg0);
            }
        });
    }


}
