package net.devwiki.recyclerview.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.devwiki.log.DevLog;
import net.devwiki.recyclerview.R;
import net.devwiki.recyclerview.single.SingleHolder;

/**
 * SingleItem的点击事件
 * Created by zyz on 2016/7/20.
 */

public class ChatItemClickSupport {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                if (holder != null) {
                    int position = holder.getAdapterPosition();
                    if (v.getId() == R.id.name_tv) {
                        mOnItemClickListener.onNameClick(mRecyclerView, position, v);
                    }else if (v.getId() == R.id.content_tv){
                        mOnItemClickListener.onTextClick(mRecyclerView, position, v);
                    }else if (v.getId() == R.id.content_iv) {
                        mOnItemClickListener.onImageClick(mRecyclerView, position, v);
                    } else {
                        DevLog.d("holder is null.....");
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
                if (viewHolder instanceof ChatHolder) {
                    ChatHolder chatHolder = (ChatHolder) viewHolder;
                    chatHolder.senderNameTv.setOnClickListener(mOnClickListener);
                    if (chatHolder instanceof TextHolder) {
                        TextHolder textHolder = (TextHolder) chatHolder;
                        textHolder.contentTv.setOnClickListener(mOnClickListener);
                    }
                    if (chatHolder instanceof ImageHolder) {
                        ImageHolder imageHolder = (ImageHolder) chatHolder;
                        imageHolder.contentIv.setOnClickListener(mOnClickListener);
                    }
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

    private ChatItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(net.devwiki.recycler.R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    public static ChatItemClickSupport addTo(RecyclerView view) {
        ChatItemClickSupport support = (ChatItemClickSupport) view.getTag(net.devwiki.recycler.R.id.item_click_support);
        if (support == null) {
            support = new ChatItemClickSupport(view);
        }
        return support;
    }

    public static ChatItemClickSupport removeFrom(RecyclerView view) {
        ChatItemClickSupport support = (ChatItemClickSupport) view.getTag(net.devwiki.recycler.R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public ChatItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public ChatItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(net.devwiki.recycler.R.id.item_click_support, null);
    }

    public interface OnItemClickListener {

        void onNameClick(RecyclerView recyclerView, int position, View view);

        void onTextClick(RecyclerView recyclerView, int position, View view);

        void onImageClick(RecyclerView recyclerView, int position, View view);
    }

    public interface OnItemLongClickListener {

        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}