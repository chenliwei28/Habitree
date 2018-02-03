package com.habitree.xueshu.login.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ToastUtil;


public class LoginPresenter extends BasePresenter {

    public LoginPresenter(Context context) {
        super(context);
    }

    public void login(String phone, String password){
        if (!checkPhone(phone))return;
        if (!checkPassword(password))return;
    }

    private boolean checkPhone(String phone){
        switch (CommUtil.checkPhoneNumber(phone)){
            case Constant.PHONE_EMPTY:
                ToastUtil.showToast(mContext, R.string.phone_number_must_be_not_empty);
                return false;
            case Constant.PHONE_ERROR:
                ToastUtil.showToast(mContext,R.string.error_phone_number);
                return false;
            case Constant.PHONE_RIGHT:
                return true;
            default:return true;
        }
    }

    private boolean checkPassword(String password){
        if (TextUtils.isEmpty(password)){
            ToastUtil.showToast(mContext,R.string.password_must_not_be_empty);
            return false;
        }else return true;
    }
}
