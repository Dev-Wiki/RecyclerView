package net.devwiki.recyclerview.footer;

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
 * Footer的适配器
 * Created by DevWiki on 2016/9/8.
 */

public class FooterAdapter extends BaseAdapter<String, FooterAdapter.FooterHolder> {

    public FooterAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    @Override
    public FooterHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new FooterHolder(parent, R.layout.item_footer_layout);
    }

    @Override
    public void bindCustomViewHolder(FooterHolder holder, int position) {
        holder.textTv.setText(getItem(position));
    }

    static class FooterHolder extends BaseHolder {

        @BindView(R.id.text_tv)
        TextView textTv;

        FooterHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            ButterKnife.bind(itemView);
        }
    }
}
