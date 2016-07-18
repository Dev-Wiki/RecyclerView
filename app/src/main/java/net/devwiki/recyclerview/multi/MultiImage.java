package net.devwiki.recyclerview.multi;

import android.support.annotation.DrawableRes;

/**
 * Created by Asia on 2016/7/18.
 */

public class MultiImage  extends MultiEntity {

    private int resId;

    public int getResId() {
        return resId;
    }

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }
}
