package com.habitree.xueshu.xs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.ImageUtil;

/**
 * 树
 */
public class TreeView extends LinearLayout implements View.OnClickListener {

    private TextView mTreeNameTv;
    private ImageView mTreeImg;

    private int width = 90;
    private int height = 100;

    public TreeView(Context context) {
        super(context);
        initUI(null);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(attrs);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(attrs);
    }

    private void initUI(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.tree_view, this);
        mTreeNameTv = findViewById(R.id.tree_name_tv);
        mTreeImg = findViewById(R.id.tree_img);
        setOnClickListener(this);

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TreeView);

            int type = array.getIndex(R.styleable.TreeView_tree_type);
            width = array.getDimensionPixelOffset(R.styleable.TreeView_tree_width, 90);
            height = array.getDimensionPixelOffset(R.styleable.TreeView_tree_height, 100);
            array.recycle();
        }

        mTreeImg.setLayoutParams(new LayoutParams(width,height));
    }

    @Override
    public void onClick(View view) {

    }

    public void setTreeName(String name) {
        mTreeNameTv.setText(name);
    }

    public void setTreeImg(String url, int placeIcon) {
        ImageUtil.loadImage(getContext(), url, mTreeImg, placeIcon);
    }
}
