package com.habitree.xueshu.xs.util;

import android.content.Context;

import com.habitree.xueshu.xs.Constant;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;



public class MyTagReceiver extends JPushMessageReceiver {

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        LogUtil.d("push alias is:"+jPushMessage.getAlias());
        switch (jPushMessage.getErrorCode()){
            case 6002:
                if (UserManager.getManager().getUser()!=null){
                    String alias = String.valueOf(UserManager.getManager().getUser().mem_id);
                    if (!jPushMessage.getAlias().equals(alias)){
                        JPushInterface.setAlias(context, Constant.NUM_110,alias);
                    }
                }
                break;
        }
        super.onAliasOperatorResult(context, jPushMessage);
    }
}
