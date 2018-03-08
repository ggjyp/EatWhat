package com.rickjiang.eatwhat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rickjiang.eatwhat.dao.FoodDao;
import com.rickjiang.eatwhat.dao.UserDao;
import com.rickjiang.eatwhat.entity.Food;
import com.rickjiang.eatwhat.entity.User;
import com.rickjiang.eatwhat.util.CommonAdapter;
import com.rickjiang.eatwhat.util.ViewHolder;

import java.util.List;

/**
 * Created by 45000 on 2017-07-21.
 */

public class MenuFragment extends android.support.v4.app.Fragment {

    private ListView mListView;
    private String[] foodClasses;
    private List<Food> mDatas;
    private Food food = new Food();
    private CommonAdapter<Food> foodCommonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        //菜单表
        mListView = (ListView) view.findViewById(R.id.id_lv);
        mDatas = list();
        foodCommonAdapter = new CommonAdapter<Food>(getContext(), mDatas,R.layout.item_list) {
            @Override
            public void convert(ViewHolder helper, Food item) {
                helper.setText(R.id.tv_food_name, item.getName());
                helper.setText(R.id.tv_food_class, item.getCategory());
            }
        };
        mListView.setAdapter(foodCommonAdapter);
        //添加按钮
        Button button=(Button) view.findViewById(R.id.id_btn_add);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });

        return view;
    }

    private List<Food> list() {
        FoodDao foodDao = new FoodDao(getContext());
        return foodDao.list();
    }

    public void showDialog(View v){
        FoodDao foodDao = new FoodDao(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialog = inflater.inflate(R.layout.dialog,null);

        //类名
        Spinner spinner = (Spinner) dialog.findViewById(R.id.id_sp_food_class);
        foodClasses = new String[]{"荤菜","素菜","半荤半素", "汤"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, foodClasses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                food.setCategory(foodClasses[i]);
                adapterView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setVisibility(View.VISIBLE);
            }
        } );
            //菜名
        final EditText   editText = (EditText) dialog.findViewById(R.id.et_food_name);
        builder.setTitle("添加");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                food.setName(editText.getText().toString());
                FoodDao foodDao = new FoodDao(getContext());
                foodDao.add(food);
                String toast = "添加成功!";
                Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
                //更新ListView数据
                //1.清除原数据源数据
                mDatas.clear();
                //2.将所有新数据添加至数据源
                mDatas.addAll(list());
                //3.提醒ListView更新数据显示
                foodCommonAdapter.notifyDataSetChanged();
            }
        });
        builder.setView(dialog);
        builder.show();
    }

}
