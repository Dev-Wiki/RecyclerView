package net.devwiki.recyclerview.chat;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.devwiki.recycler.listener.OnHolderClickListener;
import net.devwiki.recyclerview.R;

/**
 * 表情消息的Holder
 * Created by zyz on 2016/5/18.
 */
public class ImageHolder extends ChatHolder {

    ImageView contentIv;

    public ImageHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);
        contentIv = getView(R.id.content_iv);
    }

    @Override
    public void setData(ChatMsg data) {
        super.setData(data);
        contentIv.setImageResource(((ImageMsg)data).getResId());
    }

    @Override
    public void setOnHolderClickListener(OnImageHolderClickListener listener) {
        contentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setOnHolderClickListener() {
    }

    public interface OnImageHolderClickListener extends OnHolderClickListener {
        void onContentClick();
    }
}
