package com.darkknightf.android.vsapplication.model;

import com.darkknightf.android.vsapplication.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/28.
 */

public class GoodsItem {
    public int id;
    public int typeId;
    public int rating;
    public String name;
    public String typeName;
    public double price;
    public int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GoodsItem(int id, double price, String name , int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;


        rating = new Random().nextInt(5)+1;
    }

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;



    public static void initData(){
         String[] ss= new String[]{"餐饮","超市购","水果生鲜","下午茶","土豪特供","新店","百度配送","火锅"};
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        GoodsItem item = null;
        for(int i=1;i<5;i++){
            for(int j=1;j<7;j++){
                switch (j) {
                    case 0:
                        item = new GoodsItem(R.drawable.home_image_4,11,"11",i,"种类"+i);
                        break;
                    case 1:
                        item = new GoodsItem(R.drawable.home_image_5,12,"22",i,"种类"+i);
                        break;
                    case 2:
                        item = new GoodsItem(R.drawable.home_image_6,13,"33",i,"种类"+i);
                        break;
                    case 3:
                        item = new GoodsItem(R.drawable.home_image_7,14,"44",i,"种类"+i);
                        break;
                    case 4:
                        item = new GoodsItem(R.drawable.home_image_8,15,"55",i,"种类"+i);
                        break;
                    case 5:
                        item = new GoodsItem(R.drawable.home_image_9,16,"66",i,"种类"+i);
                        break;
                    case 6:
                        item = new GoodsItem(R.drawable.home_image_2,17,"77",i,"种类"+i);
                        break;
                }


                goodsList.add(item);
            }
            typeList.add(item);
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<GoodsItem> getGoodsList(){
        if(goodsList==null){
            initData();
        }
        return goodsList;
    }
    public static ArrayList<GoodsItem> getTypeList(){
        if(typeList==null){
            initData();
        }
        return typeList;
    }
}
