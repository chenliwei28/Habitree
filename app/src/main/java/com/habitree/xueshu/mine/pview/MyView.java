package com.habitree.xueshu.mine.pview;

import com.habitree.xueshu.xs.presenter.BaseView;


public interface MyView extends BaseView {

    interface ChangeInfoView{
        void onChangeSuccess();
        void onChangeFailed(String reason);
    }
}
