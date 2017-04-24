package com.darkknightf.android.vsapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.model.Shangjia;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ShangjiaAdapter extends ArrayAdapter<Shangjia> {
    private int resourceId;
    public ShangjiaAdapter(Context context, int resource,List<Shangjia> objects) {
        super(context, resource,objects);
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Shangjia shangjia  = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView shangjiaId = (ImageView) view.findViewById(R.id.shangjiaId);
        TextView shangjiaName = (TextView) view.findViewById(R.id.list_name);
        TextView yishou = (TextView) view.findViewById(R.id.yishou);
        TextView distant = (TextView) view.findViewById(R.id.distant);
        TextView peisong = (TextView) view.findViewById(R.id.peisong);
        TextView qisong = (TextView) view.findViewById(R.id.qisong);
        TextView time = (TextView) view.findViewById(R.id.time);
        shangjiaId.setImageResource(shangjia.getShangjiaId());
        shangjiaName.setText(shangjia.getShangjiaName());
        yishou.setText(shangjia.getYishou());
        distant.setText(shangjia.getDistance());
        peisong.setText(shangjia.getPeisong());
        qisong.setText(shangjia.getQisong());
        time.setText(shangjia.getTime());
        return view;
    }
}
