package com.habitree.xueshu.xs.view;


import android.support.v4.view.ViewPager;
import android.view.View;

public class CardPagerTransformer implements ViewPager.PageTransformer {

    final float SCALE_MAX = 0.8f;

    @Override
    public void transformPage(View page, float position) {
        float scale = (position < 0)
                ? ((1 - SCALE_MAX) * position + 1)
                : ((SCALE_MAX - 1) * position + 1);
        if (position < 0) {
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight() / 2);
        } else {
            page.setPivotX(0);
            page.setPivotY(page.getHeight() / 2);
        }
        page.setScaleX(scale);
        page.setScaleY(scale);
    }
}
