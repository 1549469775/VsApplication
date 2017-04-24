package com.darkknightf.android.vsapplication.model;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/23.
 */

public class Shangjia extends BmobObject{
    public int shangjiaId;



    public String shangjiaName;
    public String yishou,distance;
    public String qisong,peisong,time;


    public int getShangjiaId() {
        return shangjiaId;
    }

    public String getShangjiaName() {
        return shangjiaName;
    }

    public String getYishou() {
        return yishou;
    }

    public String getDistance() {
        return distance;
    }

    public String getQisong() {
        return qisong;
    }

    public String getPeisong() {
        return peisong;
    }

    public String getTime() {
        return time;
    }

    public Shangjia(int shangjiaId, String shangjiaName, String distance, String peisong, String yishou,
                    String qisong, String time){
        this.shangjiaId = shangjiaId;
        this.shangjiaName = shangjiaName;
        this.yishou = yishou;
        this.distance = distance;
        this.qisong = qisong;
        this.peisong = peisong;

        this.time = time;
    }
}
