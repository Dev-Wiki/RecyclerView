package net.devwiki.recyclerview.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础的Adapter
 * Created by zyz on 2016/5/17.
 */
public abstract class BaseAdapter<M, H extends BaseHolder<M>> extends RecyclerView.Adapter<H> {

    protected List<M> dataList;
    protected OnItemClickListener<M, H> listener;

    public interface OnItemClickListener<M, H extends BaseHolder<M>>{
        void onItemClick(H holder, M data);
    }

    /**
     * 无参数构造方法,数据调用{@link BaseAdapter#fillList(List)}填充
     */
    public BaseAdapter() {
        dataList = new ArrayList<>();
    }

    /**
     * 设置一个Item点击回调接口,数据后续调用{@link BaseAdapter#fillList(List)}填充
     * @param listener 回调接口
     */
    public BaseAdapter(OnItemClickListener<M, H> listener) {
        dataList = new ArrayList<>();
        this.listener = listener;
    }

    /**
     * 设置数据,并设置点击回调接口
     * @param list
     * @param listener
     */
    public BaseAdapter(@NonNull List<M> list, OnItemClickListener<M, H> listener) {
        this.dataList = list;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final H holder, int position) {
        holder.setData(dataList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder, dataList.get(holder.getLayoutPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public M getData(H holder) {
        return dataList.get(holder.getLayoutPosition());
    }

    public void fillList(List<M> list) {
        dataList.clear();
        dataList.addAll(list);
    }

    public void append(M data) {
        dataList.add(data);
    }

    public void appendList(List<M> list) {
        dataList.addAll(list);
    }
}
