package com.habitree.xueshu.xs.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.Utils;

/**
 * 系统通知栏对话框
 *
 * @author wuxq
 */
public class SystemNoticeDialog extends Dialog implements View.OnClickListener {

    private LinearLayout llCancel;
    private TextView tvGotoOpen;

    public SystemNoticeDialog(@NonNull Context context) {
        super(context,R.style.Dialog_Fullscreen);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.dialog_notice);
        tvGotoOpen = findViewById(R.id.tvGotoOpen);
        llCancel = findViewById(R.id.llCancel);
        llCancel.setOnClickListener(this);
        tvGotoOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.tvGotoOpen) {
            Utils.noticeSetting(getContext());
            this.dismiss();
        } else if (vid == R.id.llCancel) {
            this.dismiss();
        }
    }

}
