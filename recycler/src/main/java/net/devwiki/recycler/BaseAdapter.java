package net.devwiki.recycler;

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
    protected OnItemClickListener<H> listener;

    /**
     * 无参数构造方法,数据调用{@link BaseAdapter#fillList(List)}填充
     */
    public BaseAdapter() {
        dataList = new ArrayList<>();
    }

    public BaseAdapter(@NonNull List<M> list) {
        this.dataList = list;
    }

    /**
     * 设置一个Item点击回调接口,数据后续调用{@link BaseAdapter#fillList(List)}填充
     * @param listener 回调接口
     */
    public BaseAdapter(OnItemClickListener<H> listener) {
        dataList = new ArrayList<>();
        this.listener = listener;
    }

    /**
     * 设置数据,并设置点击回调接口
     * @param list
     * @param listener
     */
    public BaseAdapter(@NonNull List<M> list, OnItemClickListener<H> listener) {
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
                    listener.onItemClick(holder);
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

    /**
     * 更新数据
     * @param holder
     * @param data
     */
    public void updateItem(H holder, M data) {
        dataList.set(holder.getLayoutPosition(), data);
    }

    public M getItem(H holder) {
        return dataList.get(holder.getLayoutPosition());
    }

    public M getItem(int position) {
        return dataList.get(position);
    }

    /**
     * 追加一条数据
     * @param data
     */
    public void appendItem(M data) {
        dataList.add(data);
    }

    /**
     * 追加一个集合数据
     * @param list
     */
    public void appendList(List<M> list) {
        dataList.addAll(list);
    }

    public interface OnItemClickListener<H extends BaseHolder>{
        void onItemClick(H holder);
    }
}
