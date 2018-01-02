package com.habitree.xueshu.xs.util;


import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String text){
        if (context==null)return;
        if (mToast==null) mToast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        else mToast.setText(text);
        mToast.show();
    }

    public static void showToast(Context context,int stringId){
        if (context==null)return;
        if (mToast==null) mToast = Toast.makeText(context,stringId,Toast.LENGTH_SHORT);
        else mToast.setText(context.getString(stringId));
        mToast.show();
    }
}
