package com.habitree.xueshu.message.pview;

import com.habitree.xueshu.xs.presenter.BaseView;



public interface MessageView extends BaseView {
    interface CvsListView{
        void onInfoGetSuccess();
        void onInfoGetFailed(String reason);
    }
}
