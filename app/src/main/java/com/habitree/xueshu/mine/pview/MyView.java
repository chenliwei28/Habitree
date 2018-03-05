package com.habitree.xueshu.mine.pview;

import com.habitree.xueshu.xs.presenter.BaseView;


public interface MyView extends BaseView {
    interface UploadFileView{
        void onUploadSuccess();
        void onUploadFailed(String reason);
    }
}
