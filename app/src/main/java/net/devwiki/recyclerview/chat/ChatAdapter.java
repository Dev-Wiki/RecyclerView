package net.devwiki.recyclerview.chat;

import android.content.Context;
import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recyclerview.R;

/**
 * 聊天界面的Adapter
 * Created by zyz on 2016/5/18.
 */
public class ChatAdapter extends BaseAdapter<ChatMsg, ChatHolder> {

    private static final int VIEW_TEXT_LEFT = 0;
    private static final int VIEW_TEXT_RIGHT = 1;
    private static final int VIEW_IMAGE_LEFT = 2;
    private static final int VIEW_IMAGE_RIGHT = 3;


    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public ChatHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        ChatHolder holder = null;
        switch (viewType) {
            case VIEW_TEXT_LEFT:
                holder = new TextHolder(parent, R.layout.item_msg_text_left);
                break;
            case VIEW_TEXT_RIGHT:
                holder = new TextHolder(parent, R.layout.item_msg_text_right);
                break;
            case VIEW_IMAGE_LEFT:
                holder = new ImageHolder(parent, R.layout.item_msg_img_left);
                break;
            case VIEW_IMAGE_RIGHT:
                holder = new ImageHolder(parent, R.layout.item_msg_img_right);
                break;
        }
        return holder;
    }

    @Override
    public void bindCustomViewHolder(ChatHolder holder, int position) {
        ChatMsg data = getItem(position);
        holder.senderNameTv.setText(data.getSenderName());
        holder.createTimeTv.setText(data.getCreateTime());
        switch (getCustomViewType(position)) {
            case VIEW_TEXT_LEFT:
            case VIEW_TEXT_RIGHT:
                setTextMsg((TextHolder) holder, data);
                break;
            case VIEW_IMAGE_LEFT:
            case VIEW_IMAGE_RIGHT:
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
            if (position%2 == 0) {
                return VIEW_TEXT_LEFT;
            } else {
                return VIEW_TEXT_RIGHT;
            }
        } else {
            if (position%3 == 0) {
                return VIEW_IMAGE_LEFT;
            } else {
                return VIEW_IMAGE_RIGHT;
            }
        }
    }
}
