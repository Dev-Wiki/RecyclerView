package net.devwiki.recycler;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * 表格使用,高度和宽度分别取drawable的height和width
 * Created by zyz on 2016/9/19.
 */

public class GridDividerDecoration extends DividerDecoration {

    public GridDividerDecoration(Context context, int orientation) {
        super(context, orientation);
    }

    public GridDividerDecoration(Context context, int orientation, @DrawableRes int resId) {
        super(context, orientation, resId);
    }

    public GridDividerDecoration(Context context, int orientation, Drawable drawable) {
        super(context, orientation, drawable);
    }

    private boolean isLastSpan(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // 如果是最后一列，则不需要绘制右边
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // 如果是最后一列，则不需要绘制右边
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一列，则不需要绘制右边
                if (pos >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if (childCount < spanCount) {
                return true;
            } else {
                int completeRowCount = childCount / spanCount;
                int div = childCount % spanCount;
                if (div == 0) {
                    return pos >= (completeRowCount-1) * spanCount;
                } else {
                    return pos >= completeRowCount * spanCount;
                }
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // StaggeredGridLayoutManager 且纵向滚动
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount) {
                    return true;
                }
            } else {
                // StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            return pos == childCount-1;
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = 1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (mOrientation == VERTICAL_LIST) {
            if (isLastSpan(parent, itemPosition, spanCount, childCount)) {
                if (isLastRow(parent, itemPosition, spanCount, childCount)) {
                    outRect.set(mDivider.getIntrinsicWidth()/2, 0, 0, 0);
                } else {
                    outRect.set(mDivider.getIntrinsicWidth()/2, 0, 0, mDivider.getIntrinsicHeight());
                }
            } else{
                if (isLastSpan(parent, itemPosition, spanCount, childCount)) {

                } else {
                    outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
                }
            }
        } else {
            if (isLastSpan(parent, itemPosition, spanCount, childCount)) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else if (isLastRow(parent, itemPosition, spanCount, childCount)){
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
            }
        }
    }
}
