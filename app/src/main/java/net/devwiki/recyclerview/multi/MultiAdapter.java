package net.devwiki.recyclerview.multi;

import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;

/**
 * 多View的Adapter
 * Created by zyz on 2016/5/17.
 */
public class MultiAdapter extends BaseAdapter<Person, MultiHolder> {

    private static final int TYPE_LEFT = 0;

    private static final int TYPE_RIGHT = 1;

    @Override
    public MultiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MultiHolder holder;
        if (viewType == TYPE_LEFT) {
            holder = new LeftHolder(parent, R.layout.item_multi_left);
        } else {
            holder = new RightHolder(parent, R.layout.item_multi_right);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MultiHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getSex() == Person.MALE ? TYPE_LEFT : TYPE_RIGHT;
    }
}
