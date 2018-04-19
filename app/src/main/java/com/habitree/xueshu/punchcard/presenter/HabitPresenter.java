package com.habitree.xueshu.punchcard.presenter;

import android.content.Context;
import android.content.Intent;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.bean.CreateHabitResponse;
import com.habitree.xueshu.punchcard.bean.CreateOrderResponse;
import com.habitree.xueshu.punchcard.bean.GiveUpHabitResponse;
import com.habitree.xueshu.punchcard.bean.HabitDetailResponse;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.InitResponse;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.punchcard.bean.PunchCardResponse;
import com.habitree.xueshu.punchcard.bean.RecordListResponse;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UserManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HabitPresenter extends BasePresenter {

    private int mTreeId;        //树ID
    private String mDescribe;   //习惯描述
    private int mRemindTime;    //提醒时间
    private String mRepeatDays; //重复日期
    private int mRecycleDays;   //习惯天数
    private int mPrivacyType;   //隐私模式
    private int mRecordType;    //记录方式
    private int mPerMoney;      //罚金单价
    private double mTotalMoney;    //罚金总价
    private String mOrderId;       //订单ID
    private int mMemId;


    public HabitPresenter(Context context) {
        super(context);
    }

    public void initCreateHabitData(Intent intent){
        mTreeId = intent.getIntExtra(Constant.ID,0);
        mDescribe = intent.getStringExtra(Constant.TITLE);
        mRemindTime = intent.getIntExtra(Constant.REMIND,0);
        mRepeatDays = intent.getStringExtra(Constant.REPEAT);
        mRecycleDays = intent.getIntExtra(Constant.RECYCLE,0);
        mPrivacyType = intent.getIntExtra(Constant.PRIVACY,0);
        mRecordType = intent.getIntExtra(Constant.RECORD,0);
        mTotalMoney = intent.getDoubleExtra(Constant.TOTAL,0);
        mPerMoney = intent.getIntExtra(Constant.PER,0);
        mMemId = intent.getIntExtra(Constant.MEMID,0);
    }

    public void initInfo(){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().initInfo(timestamp,CommUtil.getSign(Constant.INIT_FUNCTION,timestamp))
                .enqueue(new Callback<InitResponse>() {
                    @Override
                    public void onResponse(Call<InitResponse> call, Response<InitResponse> response) {
                        if (response.body()!=null){
                            BaseApp.normalData = response.body().data;
                        }else {
                            ToastUtil.showToast(mContext,"初始化信息出错，请重新登录重试");
                        }
                    }

                    @Override
                    public void onFailure(Call<InitResponse> call, Throwable t) {
                        ToastUtil.showToast(mContext,"初始化信息出错，请重新登录重试");
                    }
                });
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

    public void getMyHabitList(final int type, int listType,final HabitView.HabitListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getMyHabitList(timestamp,CommUtil.getSign(Constant.GET_HABIT_LIST_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,1,50,type,listType)
                .enqueue(new Callback<HabitListResponse>() {
                    @Override
                    public void onResponse(Call<HabitListResponse> call, Response<HabitListResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onListGetSuccess(response.body().data,type);
                            }else {
                                view.onListGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HabitListResponse> call, Throwable t) {
                        view.onListGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    /**
     * 创建习惯惩金支付订单(预下单)
     * @param payName
     * @param view
     */
    public void createOrder(final String payName, final HabitView.CreateOrderView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .createHabitOrder(timestamp,CommUtil.getSign(Constant.CREATE_HABIT_ORDER_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,mTotalMoney,payName,mDescribe+"罚金支付",mDescribe+"罚金支付")
                .enqueue(new Callback<CreateOrderResponse>() {
                    @Override
                    public void onResponse(Call<CreateOrderResponse> call, Response<CreateOrderResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                mOrderId = response.body().data.order_id;
                                view.onOrderCreateSuccess(mOrderId);
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

    public void createHabit(final HabitView.CreateHabitView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .createHabit(timestamp,CommUtil.getSign(Constant.CREATE_HABIT_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,mTreeId,mOrderId,mDescribe,mPrivacyType,
                        mRepeatDays,mRemindTime,mRecycleDays,mRecordType,mMemId,mPerMoney)
                .enqueue(new Callback<CreateHabitResponse>() {
                    @Override
                    public void onResponse(Call<CreateHabitResponse> call, Response<CreateHabitResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onHabitCreateSuccess();
                            }else {
                                view.onHabitCreateFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateHabitResponse> call, Throwable t) {
                        view.onHabitCreateFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void punchCard(int habitId, String detail, String[] imagePaths, final HabitView.SendRecordView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .punchCard(HttpManager.getManager().stringToRequestBody(timestamp),
                        HttpManager.getManager().stringToRequestBody(CommUtil.getSign(Constant.UPLOAD_FILE_FUNCTION,timestamp)),
                        HttpManager.getManager().stringToRequestBody("3"),
                        HttpManager.getManager().stringToRequestBody("1"),
                        HttpManager.getManager().stringToRequestBody(BaseApp.imei),
                        HttpManager.getManager().stringToRequestBody(BaseApp.deviceInfo),
                        HttpManager.getManager().stringToRequestBody("okhttp/habitree.cn"),
                        HttpManager.getManager().stringToRequestBody(BaseApp.versionCode),
                        HttpManager.getManager().stringToRequestBody(BaseApp.versionName),
                        HttpManager.getManager().stringToRequestBody(UserManager.getManager().getUser().user_token),
                        HttpManager.getManager().stringToRequestBody(String.valueOf(habitId)),
                        HttpManager.getManager().stringToRequestBody(detail),
                        HttpManager.getManager().filesToList("images[]",imagePaths))
                .enqueue(new Callback<PunchCardResponse>() {
                    @Override
                    public void onResponse(Call<PunchCardResponse> call, Response<PunchCardResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onRecordSendSuccess();
                            }else {
                                view.onRecordSendFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }else{
                            view.onRecordSendFailed(mContext.getString(R.string.network_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<PunchCardResponse> call, Throwable t) {
                        view.onRecordSendFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getHabitDetail(int habitId, final HabitView.HabitDetailView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getHabitDetail(timestamp,CommUtil.getSign(Constant.GET_HABIT_DETAIL_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,habitId)
                .enqueue(new Callback<HabitDetailResponse>() {
                    @Override
                    public void onResponse(Call<HabitDetailResponse> call, Response<HabitDetailResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onHabitDetailGetSuccess(response.body().data);
                            }else {
                                view.onHabitDetailGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HabitDetailResponse> call, Throwable t) {
                        view.onHabitDetailGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void giveUpHabit(int habitId, final HabitView.GiveUpView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().giveUpHabit(timestamp,CommUtil.getSign(Constant.GIVE_UP_HABIT_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,habitId)
                .enqueue(new Callback<GiveUpHabitResponse>() {
                    @Override
                    public void onResponse(Call<GiveUpHabitResponse> call, Response<GiveUpHabitResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onGiveUpSuccess();
                            }else {
                                view.onGiveUpFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GiveUpHabitResponse> call, Throwable t) {
                        view.onGiveUpFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getRecordList(int habitId, final HabitView.RecordListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getSignRecordList(timestamp,CommUtil.getSign(Constant.GET_RECORD_LIST_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,1,100,habitId)
                .enqueue(new Callback<RecordListResponse>() {
                    @Override
                    public void onResponse(Call<RecordListResponse> call, Response<RecordListResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onRecordListGetSuccess(response.body().data);
                            }else {
                                view.onRecordListGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RecordListResponse> call, Throwable t) {
                        view.onRecordListGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getOthersHabitList(String userId, int listType,final HabitView.HabitListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getOthersHabitList(timestamp,CommUtil.getSign(Constant.GET_HABIT_LIST_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,userId,1,10,listType)
                .enqueue(new Callback<HabitListResponse>() {
                    @Override
                    public void onResponse(Call<HabitListResponse> call, Response<HabitListResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onListGetSuccess(response.body().data,0);
                            }else {
                                view.onListGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HabitListResponse> call, Throwable t) {
                        view.onListGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }
}
