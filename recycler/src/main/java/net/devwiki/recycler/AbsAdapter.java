package net.devwiki.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerView.Adapter的扩展,包含headerView/footerView等
 * Created by DevWiki on 2016/7/13.
 */

public abstract class AbsAdapter<M, VH extends BaseHolder> extends RecyclerView.Adapter<BaseHolder> {

    private static final String TAG = "AbsAdapter";

    public static final int VIEW_TYPE_HEADER = 1024;
    public static final int VIEW_TYPE_FOOTER = 1025;

    protected View headerView;
    protected View footerView;

    protected Context context;

    public AbsAdapter(Context context) {
        this.context = context;
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

    /**
     * 创建自定义的ViewHolder
     *
     * @param parent   父类容器
     * @param viewType view类型
     * @return ViewHolder
     */
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

    /**
     * 绑定自定义的ViewHolder
     *
     * @param holder   ViewHolder
     * @param position 位置
     */
    public abstract void bindCustomViewHolder(VH holder, int position);

    /**
     * 添加HeaderView
     *
     * @param headerView 顶部View对象
     */
    public void addHeaderView(View headerView) {
        if (headerView == null) {
            Log.w(TAG, "add the header view is null");
            return;
        }
        this.headerView = headerView;
        notifyDataSetChanged();
    }

    /**
     * 移除HeaderView
     */
    public void removeHeaderView() {
        if (headerView != null) {
            headerView = null;
            notifyDataSetChanged();
        }
    }

    /**
     * 添加FooterView
     *
     * @param footerView View对象
     */
    public void addFooterView(View footerView) {
        if (footerView == null) {
            Log.w(TAG, "add the footer view is null");
            return;
        }
        this.footerView = footerView;
        notifyDataSetChanged();
    }

    /**
     * 移除FooterView
     */
    public void removeFooterView() {
        if (footerView != null) {
            footerView = null;
            notifyDataSetChanged();
        }
    }

    /**
     * 获取附加View的数量,包括HeaderView和FooterView
     *
     * @return 数量
     */
    public int getExtraViewCount() {
        int extraViewCount = 0;
        if (headerView != null) {
            extraViewCount++;
        }
        if (footerView != null) {
            extraViewCount++;
        }
        return extraViewCount;
    }

    /**
     * 获取顶部附加View数量,即HeaderView数量
     *
     * @return 数量
     */
    public int getHeaderExtraViewCount() {
        return headerView == null ? 0 : 1;
    }

    /**
     * 获取底部附加View数量,即FooterView数量
     *
     * @return 数量, 0或1
     */
    public int getFooterExtraViewCount() {
        return footerView == null ? 0 : 1;
    }

    @Override
    public abstract long getItemId(int position);
}
