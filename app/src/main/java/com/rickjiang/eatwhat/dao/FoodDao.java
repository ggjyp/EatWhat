package com.rickjiang.eatwhat.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.rickjiang.eatwhat.entity.Food;
import com.rickjiang.eatwhat.entity.User;
import com.rickjiang.eatwhat.util.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 45000 on 2017-07-24.
 */

public class FoodDao {
    private Context context;
    private Dao<Food, Integer> foodDaoOpe;
    private DatabaseHelper helper;

    public FoodDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            foodDaoOpe = helper.getDao(Food.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个食物
     * @param food
     */
    public void add(Food food)
    {
        try
        {
            foodDaoOpe.create(food);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Food> list() {
        List<Food> foods = new ArrayList<>();
        try {
            foods = foodDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public void delete(Food food) {
        try {
            int result = foodDaoOpe.delete(food);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
