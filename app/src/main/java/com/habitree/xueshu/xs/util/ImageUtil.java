package com.habitree.xueshu.xs.util;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageUtil {
    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadImage(Activity activity, String url, ImageView imageView){
        Glide.with(activity).load(url).into(imageView);
    }

    public static void loadImage(Activity activity, int id, ImageView imageView){
        Glide.with(activity).load(id).into(imageView);
    }

    public static void loadImage(Fragment fragment,String url,ImageView imageView){
        Glide.with(fragment).load(url).into(imageView);
    }

    public static void loadImage(Activity activity, String url, ImageView imageView,int defaultId){
        RequestOptions options = new RequestOptions().placeholder(defaultId);
        Glide.with(activity).load(url).apply(options).into(imageView);
    }

    public static void loadImage(Fragment fragment, String url, ImageView imageView,int defaultId){
        RequestOptions options = new RequestOptions().placeholder(defaultId);
        Glide.with(fragment).load(url).apply(options).into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView,int defaultId){
        RequestOptions options = new RequestOptions().placeholder(defaultId);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
