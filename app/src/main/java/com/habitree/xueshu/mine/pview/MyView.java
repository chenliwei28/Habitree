package com.habitree.xueshu.mine.pview;

import com.habitree.xueshu.mine.bean.ChargeListResponse;
import com.habitree.xueshu.mine.bean.ForfeitListResponse;
import com.habitree.xueshu.mine.bean.Wallet;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;


public interface MyView extends BaseView {

    interface ChangeInfoView{
        void onChangeSuccess();
        void onChangeFailed(String reason);
    }

    interface ChangePaswView{
        void onChangePsSuccess();
        void onChangePsFailed(String reason);
    }

    interface MyWalletView{
        void onWalletInfoGetSuccess(Wallet wallet);
        void onWalletInfoGetFailed(String reason);
    }

    interface ChargeListView{
        void onChargeListGetSuccess(List<ChargeListResponse.Data> list);
        void onChargeListGetFailed(String reason);
    }

    interface ForfeitListView{
        void onForfeitListGetSuccess(ForfeitListResponse.Data data);
        void onForfeitListGetFailed(String reason);
    }

    interface OnTreeClickListener{
        void onTreeClick(int whichView,int whichTree);
    }

    interface OauthBindView{
        void onBindSuccess();
        void onBindFailed(String reason);
    }
}
