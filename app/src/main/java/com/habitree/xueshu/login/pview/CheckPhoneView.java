package com.habitree.xueshu.login.pview;

import com.habitree.xueshu.login.bean.Status;
import com.habitree.xueshu.xs.presenter.BaseView;

/**
 * 验证手机
 * @author wuxq
 */
public interface CheckPhoneView extends BaseView {

    /**
     * status  1表示未注册 2表示已注册
     * @param status
     */
    void onCheckSucceed(Status status);
    void onCheckFailed(String reason);
}
