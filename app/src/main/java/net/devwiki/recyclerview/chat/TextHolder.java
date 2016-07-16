package net.devwiki.recyclerview.chat;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recyclerview.R;

/**
 * 文本消息的Holder
 * Created by zyz on 2016/5/18.
 */
public class TextHolder extends ChatHolder {

    TextView contentTv;

    public TextHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);

        contentTv = getView(R.id.content_tv);
    }
}
