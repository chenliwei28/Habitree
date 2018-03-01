package com.habitree.xueshu.xs.util;


import com.habitree.xueshu.login.bean.User;

import org.litepal.crud.DataSupport;

public class UserManager {
    private static UserManager manager;
    private User user;

    private UserManager(){}

    public static UserManager getManager(){
        if (manager==null){
            synchronized (UserManager.class){
                if (manager==null){
                    manager = new UserManager();
                }
            }
        }
        return manager;
    }

    public void saveUser(User user){
        if (user!=null) {
            this.user = user;
            if (this.user.save())LogUtil.d("save user success");
            else LogUtil.d("save user failed");
        }
    }

    public void deleteUser(){
        user = null;
        DataSupport.delete(User.class,0);
    }

    public User getUser(){
        if (user==null){
            user = DataSupport.find(User.class,0);
        }
        return user;
    }
}
