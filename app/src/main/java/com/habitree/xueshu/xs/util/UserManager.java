package com.habitree.xueshu.xs.util;


import com.habitree.xueshu.login.bean.OAuth;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.mine.bean.Wallet;
import com.hyphenate.easeui.model.EasePreferenceManager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

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
            if (this.user.wallet!=null){
                this.user.wallet.save();
            }
            DataSupport.saveAll(this.user.mem_oauth);
            if (this.user.save())LogUtil.d("save user success");
            else LogUtil.d("save user failed");

            EasePreferenceManager.getInstance().setStringValue("user_nick",user.nickname);
            EasePreferenceManager.getInstance().setStringValue("user_icon",user.portrait);
        }
    }

    public void deleteUser(){
        user = null;
        DataSupport.deleteAll(User.class);
        DataSupport.deleteAll(Wallet.class);
        DataSupport.deleteAll(OAuth.class);
    }

    public User getUser(){
        if (user==null){
            user = DataSupport.findFirst(User.class);
            if (user!=null){
                user.wallet = DataSupport.findFirst(Wallet.class);
                user.mem_oauth = DataSupport.findAll(OAuth.class);
            }
        }
        return user;
    }

    public void updateUserHead(String imageUrl){
        user.portrait = imageUrl;
        user.update(1);
    }

    public void updateUserGenderAndBirth(int gender,int birthday){
        user.sex = gender;
        user.birthday = birthday;
        user.update(1);
    }

    public void updateUserNickname(String nickname){
        user.nickname = nickname;
        user.update(1);
    }

    public void updateUserWallet(Wallet balance){
        user.wallet.delete();
        balance.save();
        user.wallet = balance;
        user.update(1);
    }

    public void updateUserOauth(List<OAuth> auth){
        DataSupport.saveAll(auth);
        user.mem_oauth = auth;
        user.update(1);
    }

    /**
     * 获取头像
     * @return
     */
    public String getUserPhoto(){
        if(user != null){
            return user.portrait;
        }
        return "";
    }

    public String getUserNick(){
        if(user != null){
            return user.nickname;
        }
        return "";
    }

    public String getPhone(){
        if(user != null){
            return user.mobile;
        }
        return "";
    }

    public User updateMyInfo(FriendInfoResponse.FriendDetail detail){
        user.nickname = detail.nickname;
        user.email = detail.email;
        user.mobile = detail.mobile;
        user.username = detail.username;
        user.status = detail.status;
        user.reg_time = detail.reg_time;
        user.update_time = detail.update_time;
        user.portrait = detail.portrait;
        user.portrait_review = detail.portrait_review;
        user.is_official = detail.is_official;
        user.habit_cnt = detail.habit_cnt;
        user.expire_time = detail.expire_time;
        user.sign_cnt = String.valueOf(detail.sign_cnt);
        user.sign_rate = detail.sign_rate;
        user.going_cnt = detail.going_cnt;
        user.finish_cnt = detail.finish_cnt;
        user.update(1);
        saveUser(user);
        return user;
    }

}
