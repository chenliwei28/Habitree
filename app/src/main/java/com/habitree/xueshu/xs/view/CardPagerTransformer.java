package com.habitree.xueshu.xs.view;


import android.support.v4.view.ViewPager;
import android.view.View;

public class CardPagerTransformer implements ViewPager.PageTransformer {

    final float SCALE_MAX = 0.3f;
    private ViewPager mViewPager;

    @Override
    public void transformPage(View page, float position) {
        if (mViewPager == null) {
            mViewPager = (ViewPager) page.getParent();
        }

        int leftInScreen = page.getLeft() - mViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * SCALE_MAX / mViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);

        if (scaleFactor > 0) {
//            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }

}
