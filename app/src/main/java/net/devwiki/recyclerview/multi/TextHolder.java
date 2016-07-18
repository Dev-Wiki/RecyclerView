package net.devwiki.recyclerview.multi;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recycler.BaseHolder;
import net.devwiki.recyclerview.R;

/**
 * Created by Asia on 2016/7/18.
 */

public class TextHolder extends BaseHolder {

    TextView textView;

    public TextHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);
        textView = getView(R.id.item_text);
    }
}
