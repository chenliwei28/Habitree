package com.habitree.xueshu.xs.view;


import android.support.v4.view.ViewPager;
import android.view.View;

public class CardPagerTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.90f;
    private static final float MIN_ALPHA = 1.0f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);
//            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
            if (position < 0) {
                float scaleX = 1 + 0.1f * position;
//                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.1f * position;
//                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
