package com.darkknightf.android.vsapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.darkknightf.android.vsapplication.adapter.ShangjiaAdapter;
import com.darkknightf.android.vsapplication.R;
import com.darkknightf.android.vsapplication.Activity.TextActivity;
import com.darkknightf.android.vsapplication.model.Shangjia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    private GridView gridView;
    private LayoutInflater layoutInflater;
    private List<Shangjia> shangjiaLists = new ArrayList<Shangjia>();
    private SimpleAdapter simpleAdapter;
    private View viewGrid;
    private List<Map<String,Object>> grid_data;
    private int[] grid_icon = new int[] {R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,
            R.drawable.item5,R.drawable.item6,R.drawable.item7,R.drawable.item8};
    private String[] grid_text= new String[]{"餐饮","超市购","水果生鲜","下午茶","土豪特供","新店","百度配送","火锅"};

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        layoutInflater = LayoutInflater.from(getActivity());
        initGridView();
        simpleAdapter = new SimpleAdapter(getActivity(),grid_data,R.layout.grid_home,new String[]{"image","text"},
                new int[]{R.id.ivGrid,R.id.tvGrid});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"dsds"+position,Toast.LENGTH_SHORT).show();
            }
        });

        initListView();
        ShangjiaAdapter adapter = new ShangjiaAdapter(getActivity(),R.layout.list_food,shangjiaLists);
        ListView listView = (ListView) view.findViewById(R.id.shangjialist);
        listView.addHeaderView(viewGrid);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                switch (i) {
                    case 0:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(getActivity(), TextActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;
    }
    private void initGridView() {
        //GridView初始化
        viewGrid = layoutInflater.inflate(R.layout.home_grid,null);
        gridView = (GridView) viewGrid.findViewById(R.id.gridView);
        grid_data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < grid_icon.length;++i) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",grid_icon[i]);
            map.put("text",grid_text[i]);
            grid_data.add(map);
        }
    }
    private void initListView() {


        shangjiaLists.add(new Shangjia(R.drawable.home_image_4,"黄焖鸡米饭（青岛路店）" , "已送1418", "距离1.2m", "起送20元", "配送0元", "平均50分钟"));
        shangjiaLists.add(new Shangjia(R.drawable.home_image_5,  "老徽州咸肉菜饭骨头汤（中...", "2842", "1.8km", "20", "1", "60" ));
        shangjiaLists.add(new Shangjia(R.drawable.home_image_6, "德克士（百老汇店）" ,"611", "900m", "20", "5", "35"));
        shangjiaLists.add(new Shangjia(R.drawable.home_image_7, "大牌小厨（新街口店）", "944", "620m", "20", "8", "35"));
        shangjiaLists.add(new Shangjia(R.drawable.home_image_8, "津津咖喱（金轮店）", "855", "1.8km", "25", "5", "35"));
        shangjiaLists.add(new Shangjia(R.drawable.home_image_9, "和风便当（珠江路）", "695", "800m", "30", "5", "35"));
    }

}
