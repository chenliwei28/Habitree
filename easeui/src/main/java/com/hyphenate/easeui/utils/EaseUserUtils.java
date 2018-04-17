package com.hyphenate.easeui.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EasePreferenceManager;

public class EaseUserUtils {
    
    static EaseUserProfileProvider userProvider;
    
    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }
    
    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){
        if(userProvider != null)
            return userProvider.getUser(username);
        
        return null;
    }
    
    /**
     * set user avatar
     * @param userid
     */
    public static void setUserAvatar(Context context, String userid, ImageView imageView){
    	EaseUser user = getUserInfo(userid);
    	try{
            if(user != null && user.getAvatar() != null){
                try {
                    int avatarResId = Integer.parseInt(user.getAvatar());
                    Glide.with(context).load(avatarResId).into(imageView);
                } catch (Exception e) {
                    //use default avatar
                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ease_default_avatar);
                    if(user.getAvatar() != null){
                        Glide.with(context).load(user.getAvatar()).apply(options).into(imageView);
                    }else{
                        String name = EasePreferenceManager.getInstance().getStringValue("user_nick","");
                        if(user.getNick().equals(name)){
                            String url = EasePreferenceManager.getInstance().getStringValue("user_icon","");
                            Glide.with(context).load(url).apply(options).into(imageView);
                        }
                    }
                }
            }else{
                Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
            }
        }catch (Exception e){
    	    e.printStackTrace();
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }
    
    /**
     * set user's nickname
     */
    public static void setUserNick(String userid,TextView textView){
        if(textView != null){
        	EaseUser user = getUserInfo(userid);
        	if(user != null && user.getNick() != null){
        		textView.setText(user.getNick());
        	}else{
        		textView.setText(userid);
        	}
        }
    }
    
}
