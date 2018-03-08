package com.rickjiang.eatwhat;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.rickjiang.eatwhat.dao.UserDao;
import com.rickjiang.eatwhat.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.rickjiang.eatwhat", appContext.getPackageName());
    }

    @Test
    public void testAddUser(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        User user = new User("jyp","江依鹏");
        UserDao userDao = new UserDao(appContext);
        userDao.add(user);

    }

//    @Test
//    public void testAddUser()
//    {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        User u1 = new User("zhy", "2B青年");
//        DatabaseHelper helper = DatabaseHelper.getHelper(appContext);
//        try
//        {
//            helper.getUserDao().create(u1);
//            u1 = new User("zhy2", "2B青年");
//            helper.getUserDao().create(u1);
//            u1 = new User("zhy3", "2B青年");
//            helper.getUserDao().create(u1);
//            u1 = new User("zhy4", "2B青年");
//            helper.getUserDao().create(u1);
//            u1 = new User("zhy5", "2B青年");
//            helper.getUserDao().create(u1);
//            u1 = new User("zhy6", "2B青年");
//            helper.getUserDao().create(u1);
//
//            testList();
//
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testDeleteUser()
//    {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DatabaseHelper helper = DatabaseHelper.getHelper(appContext);
//        try
//        {
//            helper.getUserDao().deleteById(2);
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testUpdateUser()
//    {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DatabaseHelper helper = DatabaseHelper.getHelper(appContext);
//        try
//        {
//            User u1 = new User("zhy-android", "2B青年");
//            u1.setId(3);
//            helper.getUserDao().update(u1);
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testList()
//    {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DatabaseHelper helper = DatabaseHelper.getHelper(appContext);
//        try
//        {
//            User u1 = new User("zhy-android", "2B青年");
//            u1.setId(2);
//            List<User> users = helper.getUserDao().queryForAll();
//            Log.e("TAG", users.toString());
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
