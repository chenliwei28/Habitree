package com.habitree.xueshu.xs.util;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {
    public static void loadImage(Activity activity, String url, ImageView imageView){
        Glide.with(activity).load(url).into(imageView);
    }

    public static void loadImage(Fragment fragment,String url,ImageView imageView){
        Glide.with(fragment).load(url).into(imageView);
    }
}
