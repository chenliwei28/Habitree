package com.habitree.xueshu.punchcard.pview;

import com.habitree.xueshu.punchcard.bean.HabitDetailResponse;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;


public interface HabitView extends BaseView {
    interface PlantTreeView{
        void onPlantTreeGetSuccess(List<PlantTreeResponse.Tree> list);
        void onPlantTreeGetFail();
    }

    interface HabitListView{
        void onListGetSuccess(HabitListResponse.Data data,int type);
        void onListGetFailed(String reason);
    }

    interface PayWayView{
        void onPayWayGetSuccess(List<PayWayResponse.Payway> list);
        void onPayWayGetFailed(String reason);
    }

    interface CreateHabitView {
        void onHabitCreateSuccess();
        void onHabitCreateFailed(String reason);
    }

    interface SendRecordView{
        void onRecordSendSuccess();
        void onRecordSendFailed(String reason);
    }

    interface HabitDetailView{
        void onHabitDetailGetSuccess(HabitDetailResponse.HabitDetail detail);
        void onHabitDetailGetFailed(String reason);
    }
}
