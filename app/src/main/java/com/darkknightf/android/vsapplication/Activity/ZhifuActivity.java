package com.darkknightf.android.vsapplication.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.model.GoodsItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import c.b.BP;
import c.b.PListener;
import c.b.QListener;

public class ZhifuActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ArrayList<GoodsItem> dataList;
    String APPID = "f8c3bf8bdf24fada23bff3a1a965529f";
    int PLUGINVERSION = 7;
    TextView name1,price1,body1;
    Button go;
    RadioGroup type;
    EditText order1;
    ProgressDialog dialog;
    GoodsItem mItem;
    TextActivity mTextActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zhifu);
        BP.init(APPID);

        mTextActivity = new TextActivity();
        name1 = (TextView) findViewById(R.id.name1);
        price1 = (TextView) findViewById(R.id.price1);
        body1 = (TextView) findViewById(R.id.body1);
        order1 = (EditText) findViewById(R.id.order1);
        go = (Button) findViewById(R.id.go);
        type = (RadioGroup) findViewById(R.id.type1);

        String[] ss= new String[]{"餐饮","超市购","水果生鲜","下午茶","土豪特供","新店","百度配送","火锅"};
        for(int i=1;i<5;i++){
            for(int j=1;j<7;j++){
                switch (j) {
                    case 0:
                        mItem = new GoodsItem(R.drawable.home_image_4,11,"11",i,"种类"+i);
                        break;
                    case 1:
                        mItem = new GoodsItem(R.drawable.home_image_4,12,"22",i,"种类"+i);
                        break;
                    case 2:
                        mItem = new GoodsItem(R.drawable.home_image_4,13,"33",i,"种类"+i);
                        break;
                    case 3:
                        mItem = new GoodsItem(R.drawable.home_image_4,14,"44",i,"种类"+i);
                        break;
                    case 4:
                        mItem = new GoodsItem(R.drawable.home_image_4,15,"55",i,"种类"+i);
                        break;
                    case 5:
                        mItem= new GoodsItem(R.drawable.home_image_4,16,"66",i,"种类"+i);
                        break;
                    case 6:
                        mItem = new GoodsItem(R.drawable.home_image_4,17,"77",i,"种类"+i);
                        break;
                }

            }}
        String nname = mItem.getName();
        name1.setText(nname);
       double nprice = mTextActivity.getCost();

        price1.setText(String.valueOf(nprice));
        type.setOnCheckedChangeListener(this);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.getCheckedRadioButtonId() == R.id.alipay){
                    pay(true);
                }else if (type.getCheckedRadioButtonId() == R.id.wxpay){
                    pay(false);
                }else if (type.getCheckedRadioButtonId() ==R.id.query){
                    query();
                }
            }
        });

    }


    private boolean checkPackageInstalled(String packageName, String browserUrl) {
        try {
            // 检查是否有支付宝客户端
            getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // 没有安装支付宝，跳转到应用市场
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                startActivity(intent);
            } catch (Exception ee) {// 连应用市场都没有，用浏览器去支付宝官网下载
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(browserUrl));
                    startActivity(intent);
                } catch (Exception eee) {
                    Toast.makeText(ZhifuActivity.this,
                            "您的手机上没有没有应用市场也没有浏览器，我也是醉了，你去想办法安装支付宝/微信吧",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }
    private static final int REQUESTPERMISSION = 101;

    private void installApk(String s) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
        } else {
            installBmobPayPlugin(s);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTPERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installBmobPayPlugin("bp.db");
                } else {
                    //提示没有权限，安装不了
                    Toast.makeText(ZhifuActivity.this,"您拒绝了权限，这样无法安装支付插件",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    void pay(final boolean alipayOrWechatPay) {
        if (alipayOrWechatPay) {
            if (!checkPackageInstalled("com.eg.android.AlipayGphone",
                    "https://www.alipay.com")) { // 支付宝支付要求用户已经安装支付宝客户端
                Toast.makeText(ZhifuActivity.this, "请安装支付宝客户端", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        } else {
            if (checkPackageInstalled("com.tencent.mm", "http://weixin.qq.com")) {// 需要用微信支付时，要安装微信客户端，然后需要插件
                // 有微信客户端，看看有无微信支付插件
                int pluginVersion = BP.getPluginVersion(this);
                if (pluginVersion < PLUGINVERSION) {// 为0说明未安装支付插件,
                    // 否则就是支付插件的版本低于官方最新版
                    Toast.makeText(
                            ZhifuActivity.this,
                            pluginVersion == 0 ? "监测到本机尚未安装支付插件,无法进行支付,请先安装插件(无流量消耗)"
                                    : "监测到本机的支付插件不是最新版,最好进行更新,请先更新插件(无流量消耗)",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");

                    installApk("bp.db");
                    return;
                }
            } else {// 没有安装微信
                Toast.makeText(ZhifuActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion());
        final String name = getName();

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.bmob.app.sport",
                    "com.bmob.app.sport.wxapi.BmobActivity");
            intent.setComponent(cn);
            this.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        BP.pay(name, getBody(), getPrice(), alipayOrWechatPay, new PListener() {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            @Override
            public void unknow() {
                Toast.makeText(ZhifuActivity.this, "支付结果未知,请稍后手动查询", Toast.LENGTH_SHORT)
                        .show();
                //tv.append(name + "'s pay status is unknow\n\n");
                hideDialog();
            }

            // 支付成功,如果金额较大请手动查询确认
            @Override
            public void succeed() {
                Toast.makeText(ZhifuActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
                //tv.append(name + "'s pay status is success\n\n");
                hideDialog();
            }

            // 无论成功与否,返回订单号
            @Override
            public void orderId(String orderId) {
                // 此处应该保存订单号,比如保存进数据库等,以便以后查询
                order1.setText(orderId);
                //tv.append(name + "'s orderid is " + orderId + "\n\n");
                showDialog("获取订单成功!请等待跳转到支付页面~");
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            @Override
            public void fail(int code, String reason) {

                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    Toast.makeText(
                            ZhifuActivity.this,
                            "监测到你尚未安装支付插件,无法进行支付,请先安装插件(已打包在本地,无流量消耗),安装结束后重新支付",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");
                    installApk("bp.db");
                } else {
                    Toast.makeText(ZhifuActivity.this, "支付中断!", Toast.LENGTH_SHORT)
                            .show();
                }
                //tv.append(name + "'s pay status is fail, error code is \n"
                       // + code + " ,reason is " + reason + "\n\n");
                hideDialog();
            }
        });
    }

    // 执行订单查询
    void query() {
        showDialog("正在查询订单...");
        final String orderId = getOrder();

        BP.query(orderId, new QListener() {

            @Override
            public void succeed(String status) {
                Toast.makeText(ZhifuActivity.this, "查询成功!该订单状态为 : " + status,
                        Toast.LENGTH_SHORT).show();
                //tv.append("pay status of" + orderId + " is " + status + "\n\n");
                hideDialog();
            }

            @Override
            public void fail(int code, String reason) {
                Toast.makeText(ZhifuActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                //tv.append("query order fail, error code is " + code
                      //  + " ,reason is \n" + reason + "\n\n");
                hideDialog();
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.alipay:
                // 以下仅为控件操作，可以略过
                name1.setVisibility(View.VISIBLE);
                price1.setVisibility(View.VISIBLE);
                body1.setVisibility(View.VISIBLE);
                order1.setVisibility(View.GONE);
                go.setText("支付宝支付");
                break;
            case R.id.wxpay:
                // 以下仅为控件操作，可以略过
                name1.setVisibility(View.VISIBLE);
                price1.setVisibility(View.VISIBLE);
                body1.setVisibility(View.VISIBLE);
                order1.setVisibility(View.GONE);
                go.setText("微信支付");
                break;
            case R.id.query:
                // 以下仅为控件操作，可以略过
                name1.setVisibility(View.GONE);
                price1.setVisibility(View.GONE);
                body1.setVisibility(View.GONE);
                order1.setVisibility(View.VISIBLE);
                go.setText("订单查询");
                break;

            default:
                break;
        }
    }
    double getPrice() {
        double price = 0.02;
        try {
            price = Double.parseDouble(this.price1.getText().toString());
        } catch (NumberFormatException e) {
        }
        return price;
    }

    // 商品详情(可不填)
    String getName() {
        return this.name1.getText().toString();
    }

    // 商品详情(可不填)
    String getBody() {
        return this.body1.getText().toString();
    }

    // 支付订单号(查询时必填)
    String getOrder() {
        return this.order1.getText().toString();
    }

    void showDialog(String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(this);
                dialog.setCancelable(true);
            }
            dialog.setMessage(message);
            dialog.show();
        } catch (Exception e) {
            // 在其他线程调用dialog会报错
        }
    }

    void hideDialog() {
        if (dialog != null && dialog.isShowing())
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
    }

    /**
     * 安装assets里的apk文件
     *
     * @param fileName
     */
    void installBmobPayPlugin(String fileName) {
        try {
            InputStream is = getAssets().open(fileName);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + fileName + ".apk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + file),
                    "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
