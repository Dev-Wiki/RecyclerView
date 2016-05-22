package net.devwiki.recyclerview.chat;

import android.support.annotation.DrawableRes;

/**
 * 代表一条图片消息
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
