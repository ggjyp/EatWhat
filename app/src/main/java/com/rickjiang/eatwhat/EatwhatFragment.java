package com.rickjiang.eatwhat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rickjiang.eatwhat.dao.FoodDao;
import com.rickjiang.eatwhat.dao.UserDao;
import com.rickjiang.eatwhat.entity.Food;
import com.rickjiang.eatwhat.entity.User;
import com.rickjiang.eatwhat.util.CommonAdapter;
import com.rickjiang.eatwhat.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 45000 on 2017-07-21.
 */

public class EatwhatFragment extends android.support.v4.app.Fragment {

    private Button mBtnGenerator;

    private List<Food> allFoods = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();

    private ListView mListView;
    private CommonAdapter<Food> foodCommonAdapter;

    private RadioGroup radioGroupCategory;
    private RadioGroup radioGroupNumber;
    private RadioButton radioButtonCategory;
    private RadioButton radioButtonNum;

    private String category = "荤菜";
    private String num = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.eatwhat_fragment, container, false);
        addListenerRadioGroupCategory(view);
        addListenerRadioGroupNumber(view);

        mListView = (ListView) view.findViewById(R.id.tv_menu_generated);
//        foods = getRandomList(generatorMenu("荤菜"),2);
        foodCommonAdapter = new CommonAdapter<Food>(getContext(), foods,R.layout.item_list) {
            @Override
            public void convert(ViewHolder helper, Food item) {
                helper.setText(R.id.tv_food_name, item.getName());
                helper.setText(R.id.tv_food_class, item.getCategory());
            }
        };
        mListView.setAdapter(foodCommonAdapter);

        mBtnGenerator = (Button) view.findViewById(R.id.id_btn_generator);
        mBtnGenerator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                foods.clear();
                foods.addAll(getRandomList(generatorMenu(category),Integer.valueOf(num)));
                foodCommonAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }


    private void addListenerRadioGroupCategory(final View view) {
        radioGroupCategory = (RadioGroup) view.findViewById(R.id.rg_category);
        radioGroupCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) view.findViewById(checkedId);
                category = rb.getText().toString();
            }
        });
    }

    private void addListenerRadioGroupNumber(final View view) {
        radioGroupNumber = (RadioGroup) view.findViewById(R.id.rg_number);
        radioGroupNumber.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) view.findViewById(checkedId);
                num = rb.getText().toString();
            }
        });
    }



    /**
     * 读取指定类别的菜品
     * @param category
     * @return
     */
    public List<Food> generatorMenu(String category) {
        FoodDao foodDao = new FoodDao(getContext());
        List<Food> foods = new ArrayList<>();
        for (Food food : foodDao.list()) {
            if (food.getCategory().equals(category)) {
                foods.add(food);
            }
        }
        return foods;
    }

    /**
     * @function:从list中随机抽取若干不重复元素
     *
     * @param paramList:被抽取list
     * @param count:抽取元素的个数
     * @return:由抽取元素组成的新list
     */
    public static List<Food> getRandomList(List<Food> paramList,int count){
        if(paramList.size()<count){
            return paramList;
        }
        Random random=new Random();
        List<Integer> tempList=new ArrayList<Integer>();
        List<Food> newList=new ArrayList<Food>();
        int temp=0;
        for(int i=0;i<count;i++){
            temp=random.nextInt(paramList.size());//将产生的随机数作为被抽list的索引
            if(!tempList.contains(temp)){
                tempList.add(temp);
                newList.add(paramList.get(temp));
            }
            else{
                i--;
            }
        }
        return newList;
    }

}
