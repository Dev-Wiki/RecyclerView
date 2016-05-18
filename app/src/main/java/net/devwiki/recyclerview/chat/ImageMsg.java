package net.devwiki.recyclerview.chat;

import android.support.annotation.DrawableRes;

/**
 * Created by zyz on 2016/5/18.
 */
public class ImageMsg extends ChatMsg {

    @DrawableRes
    private int resId;

    public int getResId() {
        return resId;
    }

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }
}
