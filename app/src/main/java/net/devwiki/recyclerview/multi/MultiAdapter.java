package net.devwiki.recyclerview.multi;

import android.content.Context;
import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.BaseHolder;
import net.devwiki.recyclerview.R;

/**
 * Created by Asia on 2016/7/18.
 */

public class MultiAdapter extends BaseAdapter<MultiEntity, BaseHolder> {

    private static final int VIEW_TEXT = 0;
    private static final int VIEW_IMAGE = 1;

    public MultiAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCustomViewType(int position) {
        MultiEntity entity = getItem(position);
        if (entity instanceof MultiText) {
            return VIEW_TEXT;
        }
        return VIEW_IMAGE;
    }

    @Override
    public BaseHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TEXT) {
            return new TextHolder(parent, R.layout.item_multi_text);
        } else {
            return new ImageHolder(parent, R.layout.item_multi_image);
        }
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, int position) {
        MultiEntity entity = getItem(position);
        if (getCustomViewType(position) == VIEW_TEXT) {
            MultiText text = (MultiText) entity;
            TextHolder textHolder = (TextHolder) holder;
            textHolder.textView.setText(text.getContent());
        } else {
            MultiImage image = (MultiImage) entity;
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.imageView.setImageResource(image.getResId());
        }
    }
}
