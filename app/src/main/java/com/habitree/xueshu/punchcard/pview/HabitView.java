package com.habitree.xueshu.punchcard.pview;

import com.habitree.xueshu.punchcard.bean.CreateOrderResponse;
import com.habitree.xueshu.punchcard.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;


public interface HabitView extends BaseView {
    interface PlantTreeView{
        void onPlantTreeGetSuccess(List<PlantTreeResponse.Data> list);
        void onPlantTreeGetFail();
    }

    interface HabitListView{
        void onListGetSuccess();
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
}
