package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView.ChangeInfoView;
import com.habitree.xueshu.punchcard.activity.HabitSettingActivity;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.UserManager;

/**
 * 昵称设置界面
 *
 * @author wuxq
 */

public class NickNameActivity extends BaseActionBarActivity implements ChangeInfoView {

    private EditText etNickName;
    private MyPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_nickname;
    }

    @Override
    protected void initView() {
        etNickName = findViewById(R.id.etNickName);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        try {
            setTitle("设置昵称");
            User user = UserManager.getManager().getUser();
            if (user != null && user.nickname != null) {
                etNickName.setText(user.nickname);
                etNickName.setSelection(user.nickname.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_save, menu);
        MenuItem item = menu.findItem(R.id.tvSave);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String nickName = etNickName.getText().toString();
                if (TextUtils.isEmpty(nickName)) {
                    showToast("昵称不能为空");
                    return false;
                }
                mPresenter.changeNickname(nickName, NickNameActivity.this);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onChangeSuccess() {
        showToast(getString(R.string.change_success));
        setResult(Constant.NUM_109, new Intent(NickNameActivity.this, HabitSettingActivity.class));
        onBackClick();
    }

    @Override
    public void onChangeFailed(String reason) {
        showToast(reason);
    }
}
