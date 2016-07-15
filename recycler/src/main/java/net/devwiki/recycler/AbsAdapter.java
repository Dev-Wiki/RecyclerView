package net.devwiki.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by zyz on 2016/7/13.
 */

public abstract class AbsAdapter<M, VH extends BaseHolder> extends RecyclerView.Adapter<BaseHolder> {

    public static final int VIEW_TYPE_HEADER = 1024;
    public static final int VIEW_TYPE_FOOTER = 1025;

    private List<M> dataList;

    private View headerView;
    private View footerView;

    public AbsAdapter() {
        dataList = new ArrayList<>();
    }

    public AbsAdapter(List<M> list) {
        dataList = new ArrayList<>();
        dataList.addAll(list);
    }

    @Override
    public final BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new BaseHolder(headerView);
        } else if (viewType == VIEW_TYPE_FOOTER) {
            return new BaseHolder(footerView);
        } else {
            return createCustomViewHolder(parent, viewType);
        }
    }

    public abstract VH createCustomViewHolder(ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(BaseHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
            case VIEW_TYPE_FOOTER:
                break;
            default:
                bindCustomViewHolder((VH) holder, position);
                break;
        }
    }

    public abstract void bindCustomViewHolder(VH holder, int position);

    private int getExtraViewCount() {
        int extraViewCount = 0;
        if (headerView != null) {
            extraViewCount++;
        }
        if (footerView != null) {
            extraViewCount++;
        }
        return extraViewCount;
    }

    private int getHeaderExtraViewCount() {
        return headerView == null ? 0 : 1;
    }

    private int getFooterExtraViewCount() {
        return footerView == null ? 0 : 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList.size() + getExtraViewCount();
    }

    public M getItem(int position) {
        if (getHeaderExtraViewCount() > 0 && position ==0 ) {
            return null;
        }
        if (getFooterExtraViewCount() > 0 && position == getItemCount() - 1) {
            return null;
        }
        if (position > getItemCount() - 1) {
            return null;
        }
        return dataList.get(position - getHeaderExtraViewCount());
    }
}
