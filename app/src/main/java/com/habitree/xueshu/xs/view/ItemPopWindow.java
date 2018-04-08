package com.habitree.xueshu.xs.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.habitree.xueshu.R;

public class ItemPopWindow extends PopupWindow{

    private Context context;
    private OnCheckedListener listener;

    public ItemPopWindow(Context context,OnCheckedListener listener){
        this.context = context;
        this.listener = listener;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popwindow_item,null);
        setContentView(view);
        RadioGroup group = view.findViewById(R.id.choose_rg);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (listener!=null){
                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.all_rb:
                            listener.onChecked(0);
                            break;
                        case R.id.top_up_rb:
                            listener.onChecked(1);
                            break;
                        case R.id.withdraw_rb:
                            listener.onChecked(2);
                            break;
                        case R.id.forfeit_rb:
                            listener.onChecked(3);
                            break;
                    }
                }
            }
        });
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置弹出窗体可点击
        this.setFocusable(true);
        setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
    }

    public interface OnCheckedListener{
        void onChecked(int i);
    }
}
