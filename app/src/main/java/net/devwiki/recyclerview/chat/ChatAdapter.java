package net.devwiki.recyclerview.chat;

import android.content.Context;
import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.listener.OnItemClickListener;
import net.devwiki.recyclerview.R;

/**
 * 聊天界面的Adapter
 * Created by zyz on 2016/5/18.
 */
public class ChatAdapter extends BaseAdapter<ChatMsg, ChatHolder> {

    private static final int VIEW_TEXT = 0;
    private static final int VIEW_IMAGE = 1;

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public ChatHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        ChatHolder holder;
        if (viewType == VIEW_IMAGE) {
            holder = new ImageHolder(parent, R.layout.item_msg_img_left);
        } else {
            holder = new TextHolder(parent, R.layout.item_msg_text_left);
        }
        return holder;
    }

    @Override
    public void bindCustomViewHolder(ChatHolder holder, int position) {
        ChatMsg data = getItem(position);
        holder.senderNameTv.setText(data.getSenderName());
        holder.createTimeTv.setText(data.getCreateTime());
        switch (getCustomViewType(position)) {
            case VIEW_TEXT:
                setTextMsg((TextHolder) holder, data);
                break;
            case VIEW_IMAGE:
                setImageMsg((ImageHolder) holder, data);
                break;
            default:
                break;
        }
    }

    private void setTextMsg(TextHolder holder, ChatMsg data) {
        holder.contentTv.setText(((TextMsg)data).getText());
    }

    private void setImageMsg(ImageHolder holder, ChatMsg data) {
        holder.contentIv.setImageResource(((ImageMsg)data).getResId());
    }

    @Override
    public int getCustomViewType(int position) {
        if (getItem(position).getMsgType() == ChatMsg.TYPE_TEXT) {
            return VIEW_TEXT;
        } else {
            return VIEW_IMAGE;
        }
    }
}
