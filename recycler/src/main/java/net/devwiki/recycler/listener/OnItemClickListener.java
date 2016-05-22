package net.devwiki.recycler.listener;

import net.devwiki.recycler.BaseHolder;

/**
 * Item点击时回调接口
 * Created by zyz on 2016/5/22.
 */
public interface OnItemClickListener<H extends BaseHolder> {
    /**
     * 当item被点击时回调
     *
     * @param holder item对应的holder
     */
    void onItemClick(H holder);

}
