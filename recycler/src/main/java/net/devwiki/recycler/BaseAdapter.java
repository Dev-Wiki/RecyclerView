package net.devwiki.recycler;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.devwiki.recycler.listener.OnItemClickListener;

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
     * 设置数据,并设置点击回调接口
     *
     * @param list 数据集合
     * @param listener 回调接口
     */
    public BaseAdapter(@Nullable List<M> list, @Nullable OnItemClickListener<H> listener) {
        this.dataList = list;
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }

        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final H holder, int position) {
        holder.setData(dataList.get(position));
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 填充数据,此方法会清空以前的数据
     *
     * @param list 需要显示的数据
     */
    public void fillList(List<M> list) {
        dataList.clear();
        dataList.addAll(list);
    }

    /**
     * 更新数据
     *
     * @param holder item对应的holder
     * @param data   item的数据
     */
    public void updateItem(H holder, M data) {
        dataList.set(holder.getLayoutPosition(), data);
    }

    /**
     * 获取一条数据
     *
     * @param holder item对应的holder
     * @return 该item对应的数据
     */
    public M getItem(H holder) {
        return dataList.get(holder.getLayoutPosition());
    }

    /**
     * 获取一条数据
     *
     * @param position item的位置
     * @return item对应的数据
     */
    public M getItem(int position) {
        return dataList.get(position);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void appendItem(M data) {
        dataList.add(data);
    }

    /**
     * 追加一个集合数据
     *
     * @param list 要追加的数据集合
     */
    public void appendList(List<M> list) {
        dataList.addAll(list);
    }

    /**
     * 在最顶部前置数据
     *
     * @param data 要前置的数据
     */
    public void preposeItem(M data) {
        dataList.add(0, data);
    }

    /**
     * 在顶部前置数据集合
     *
     * @param list 要前置的数据集合
     */
    public void preposeList(List<M> list) {
        dataList.addAll(0, list);
    }

}
