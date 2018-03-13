package com.habitree.xueshu.punchcard.presenter;

import android.content.Context;

import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

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
                        if (response.body()!=null&&response.body().status==200){
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

    public void getMyHabitList(){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getMyHabitList(timestamp,CommUtil.getSign(Constant.GET_HABIT_LIST_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,1,10)
                .enqueue(new Callback<HabitListResponse>() {
                    @Override
                    public void onResponse(Call<HabitListResponse> call, Response<HabitListResponse> response) {
                        if (response.body()!=null){

                        }
                    }

                    @Override
                    public void onFailure(Call<HabitListResponse> call, Throwable t) {

                    }
                });
    }
}
