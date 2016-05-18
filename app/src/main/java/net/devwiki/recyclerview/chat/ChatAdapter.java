package net.devwiki.recyclerview.chat;

import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recyclerview.R;

/**
 * 聊天界面的Adapter
 * Created by zyz on 2016/5/18.
 */
public class ChatAdapter extends BaseAdapter<ChatMsg, ChatHolder> {

    private static final int VIEW_TEXT = 0;
    private static final int VIEW_IMAGE = 1;

    public ChatAdapter(OnItemClickListener<ChatHolder> listener) {
        super(listener);
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatHolder holder;
        if (viewType == VIEW_IMAGE) {
            holder = new ImageHolder(parent, R.layout.item_msg_img_left);
        } else {
            holder = new TextHolder(parent, R.layout.item_msg_text_left);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getMsgType() == ChatMsg.TYPE_TEXT) {
            return VIEW_TEXT;
        } else {
            return VIEW_IMAGE;
        }
    }
}
