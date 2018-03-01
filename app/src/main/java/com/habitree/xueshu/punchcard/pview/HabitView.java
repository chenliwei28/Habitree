package com.habitree.xueshu.punchcard.pview;

import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;


public interface HabitView extends BaseView {
    interface PlantTreeView{
        void onPlantTreeGetSuccess(List<PlantTreeResponse.DataBean> list);
        void onPlantTreeGetFail();
    }
}
