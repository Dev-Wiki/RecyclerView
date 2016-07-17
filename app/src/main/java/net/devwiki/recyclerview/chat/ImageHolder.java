package net.devwiki.recyclerview.chat;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.devwiki.recyclerview.R;

/**
 * 表情消息的Holder
 * Created by zyz on 2016/5/18.
 */
public class ImageHolder extends ChatHolder {

    ImageView contentIv;

    public ImageHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);
        contentIv = getView(R.id.content_iv);
    }
}
