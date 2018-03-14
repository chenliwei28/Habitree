package com.habitree.xueshu.punchcard.presenter;

import android.content.Context;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.CreateOrderResponse;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.PayWayResponse;
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

    public void getPayMode(final HabitView.PayWayView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getPayWay(timestamp,CommUtil.getSign(Constant.GET_PAYWAY_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<PayWayResponse>() {
                    @Override
                    public void onResponse(Call<PayWayResponse> call, Response<PayWayResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onPayWayGetSuccess(response.body().data);
                            }else {
                                view.onPayWayGetFailed(mContext.getString(R.string.network_error));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PayWayResponse> call, Throwable t) {
                        view.onPayWayGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void createOrder(int totalMoney, String payName, String title, final HabitView.CreateOrderView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .createHabitOrder(timestamp,CommUtil.getSign(Constant.CREATE_HABIT_ORDER_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,totalMoney,payName,title+"罚金支付",title+"罚金支付")
                .enqueue(new Callback<CreateOrderResponse>() {
                    @Override
                    public void onResponse(Call<CreateOrderResponse> call, Response<CreateOrderResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onOrderCreateSuccess(response.body().data);
                            }else {
                                view.onOrderCreateFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateOrderResponse> call, Throwable t) {
                        view.onOrderCreateFailed(mContext.getString(R.string.network_error));
                    }
                });
    }
}
