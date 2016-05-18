package net.devwiki.recyclerview.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基础的ViewHolder
 * Created by zyz on 2016/5/17.
 */
public abstract class BaseHolder<M> extends RecyclerView.ViewHolder {

    public BaseHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    /**
     * 获取布局中的View
     * @param viewId view的Id
     * @param <T> View的类型
     * @return view
     */
    protected <T extends View>T getView(@IdRes int viewId){
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 获取Context实例
     * @return context
     */
    protected Context getContext() {
        return itemView.getContext();
    }

    /**
     * 设置数据
     * @param data 要显示的数据对象
     */
    public abstract void setData(M data);
}
