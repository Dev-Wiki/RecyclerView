package net.devwiki.recyclerview.scroll;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.BaseHolder;
import net.devwiki.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DevWiki on 2016/9/10.
 */

public class ScrollAdapter extends BaseAdapter<String, ScrollAdapter.ScrollHolder> {

    public ScrollAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    @Override
    public ScrollHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new ScrollHolder(parent, R.layout.item_scroll);
    }

    @Override
    public void bindCustomViewHolder(ScrollHolder holder, int position) {
        holder.scrollTv.setText(getItem(position));
    }

    static class ScrollHolder extends BaseHolder {

        TextView scrollTv;

        ScrollHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            scrollTv = getView(R.id.scroll_tv);
        }
    }
}
