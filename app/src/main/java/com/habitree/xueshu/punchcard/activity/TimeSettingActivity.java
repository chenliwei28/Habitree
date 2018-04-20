package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.InitResponse;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomRadioGroup;

import java.util.List;

/**
 * 持续时间
 */
public class TimeSettingActivity extends BaseActionBarActivity implements OnClickListener,TextWatcher {

    private TextView mConfirmTv;
    private CustomRadioGroup mDaysCrg;
    private EditText etCount;
    private int mDays;
    private RadioButton[] btns;
    private RadioButton seven,fifteen,thirty,sixty,ninety,hundred;
    private List<InitResponse.Data.RecycleDays> mList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_time_setting;
    }

    @Override
    protected void initView() {
        mConfirmTv = findViewById(R.id.confirm_tv);
        mDaysCrg = findViewById(R.id.days_crg);
        etCount = findViewById(R.id.etCount);
        seven = findViewById(R.id.seven);
        fifteen = findViewById(R.id.fifteen);
        thirty = findViewById(R.id.thirty);
        sixty = findViewById(R.id.sixty);
        ninety = findViewById(R.id.ninety);
        hundred = findViewById(R.id.hundred);
        btns = new RadioButton[]{seven,fifteen,thirty,sixty,ninety,hundred};
    }

    @Override
    protected void initListener() {
        etCount.addTextChangedListener(this);
        mConfirmTv.setOnClickListener(this);
        mList = BaseApp.normalData.recycle_days;
        for (int i = 0;i<6;i++){
            btns[i].setText(mList.get(i).title);
        }
        mDaysCrg.setOnCheckedChangeListener(new CustomRadioGroup.OnCheckChangeListener() {
            @Override
            public void onCheckChange(RadioButton button) {
                for (RadioButton btn : btns) {
                    btn.setBackgroundResource(R.drawable.bg_time_radio_button);
                }
                etCount.setText("");
                switch (button.getId()){
                    case R.id.seven:
                        mDays = mList.get(0).day;
                        break;
                    case R.id.fifteen:
                        mDays = mList.get(1).day;
                        break;
                    case R.id.thirty:
                        mDays = mList.get(2).day;
                        break;
                    case R.id.sixty:
                        mDays = mList.get(3).day;
                        break;
                    case R.id.ninety:
                        mDays = mList.get(4).day;
                        break;
                    case R.id.hundred:
                        mDays = mList.get(5).day;
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.time_of_duration);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_tv:
                if (mDays==0){
                    showToast(getString(R.string.please_choose_recycle_days));
                }else {
                    setResult(Constant.NUM_110,new Intent(TimeSettingActivity.this,HabitSettingActivity.class).putExtra(Constant.POSITION,mDays));
                    AppManager.getAppManager().finishActivity(TimeSettingActivity.this);
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString();
        if(!TextUtils.isEmpty(str)){
            for (RadioButton btn : btns) {
                btn.setChecked(false);
            }
            mDays = Integer.valueOf(str);
        }
    }
}
