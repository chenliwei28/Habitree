package com.habitree.xueshu.punchcard.presenter;

import android.content.Context;

import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HabitPresenter extends BasePresenter {

    public HabitPresenter(Context context) {
        super(context);
    }

    public void getPlantTree(final HabitView.PlantTreeView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .getPlantTree(timestamp, CommUtil.getSign(Constant.PLANT_TREE_FUNCTION,timestamp))
                .enqueue(new Callback<PlantTreeResponse>() {
                    @Override
                    public void onResponse(Call<PlantTreeResponse> call, Response<PlantTreeResponse> response) {
                        if (response.body().status==200){
                            view.onPlantTreeGetSuccess(response.body().data);
                        }else {
                            view.onPlantTreeGetFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlantTreeResponse> call, Throwable t) {
                        view.onPlantTreeGetFail();
                    }
                });
    }
}
