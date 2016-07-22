package net.devwiki.recyclerview.single;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.devwiki.recyclerview.R;

/**
 * SingleItem的点击事件
 * Created by zyz on 2016/7/20.
 */

public class SingleItemClickSupport {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                if (holder != null) {
                    if (v.getId() == R.id.name_tv) {
                        mOnItemClickListener.onNameClicked(mRecyclerView, holder.getAdapterPosition(), v);
                    } else {
                        mOnItemClickListener.onAgeClicked(mRecyclerView, holder.getAdapterPosition(), v);
                    }
                }
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(view);
                if (viewHolder instanceof SingleHolder) {
                    SingleHolder singleHolder = (SingleHolder) viewHolder;
                    singleHolder.nameView.setOnClickListener(mOnClickListener);
                    singleHolder.ageView.setOnClickListener(mOnClickListener);
                }
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private SingleItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(net.devwiki.recycler.R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    public static SingleItemClickSupport addTo(RecyclerView view) {
        SingleItemClickSupport support = (SingleItemClickSupport) view.getTag(net.devwiki.recycler.R.id.item_click_support);
        if (support == null) {
            support = new SingleItemClickSupport(view);
        }
        return support;
    }

    public static SingleItemClickSupport removeFrom(RecyclerView view) {
        SingleItemClickSupport support = (SingleItemClickSupport) view.getTag(net.devwiki.recycler.R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public SingleItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public SingleItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(net.devwiki.recycler.R.id.item_click_support, null);
    }

    public interface OnItemClickListener {

        void onNameClicked(RecyclerView recyclerView, int position, View view);

        void onAgeClicked(RecyclerView recyclerView, int position, View view);
    }

    public interface OnItemLongClickListener {

        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}