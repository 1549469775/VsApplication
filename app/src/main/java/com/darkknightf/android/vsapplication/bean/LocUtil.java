package com.darkknightf.android.vsapplication.bean;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LocUtil {
    private static Loc loc= null ;

    public static Loc getLoc() {
        if (loc==null){
            loc=new Loc();
        }
        return loc;
    }

    public static void setLoc(Loc loc) {
        LocUtil.loc = loc;
    }
}
