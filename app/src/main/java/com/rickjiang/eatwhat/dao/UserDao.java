package com.rickjiang.eatwhat.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.rickjiang.eatwhat.util.DatabaseHelper;
import com.rickjiang.eatwhat.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 45000 on 2017-07-24.
 */

public class UserDao {
    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public UserDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     * @param user
     */
    public void add(User user)
    {
        try
        {
            userDaoOpe.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public List<User> list() {
        List<User> users = new ArrayList<>();
        try {
            users = userDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
