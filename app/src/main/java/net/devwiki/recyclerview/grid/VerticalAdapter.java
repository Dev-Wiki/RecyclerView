package net.devwiki.recyclerview.grid;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.BaseHolder;
import net.devwiki.recyclerview.R;

/**
 * 垂直表格的Adapter
 * Created by DevWiki on 2016/9/19.
 */

public class VerticalAdapter extends BaseAdapter<String, VerticalAdapter.DividerHolder>{

    public VerticalAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    @Override
    public DividerHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new DividerHolder(parent, R.layout.item_divier);
    }

    @Override
    public void bindCustomViewHolder(DividerHolder holder, int position) {
        holder.dividerTv.setText(getItem(position));
    }

    class DividerHolder extends BaseHolder{

        TextView dividerTv;
        public DividerHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            dividerTv = getView(R.id.item_tv);
        }
    }
}
