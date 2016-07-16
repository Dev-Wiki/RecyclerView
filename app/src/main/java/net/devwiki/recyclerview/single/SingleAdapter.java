package net.devwiki.recyclerview.single;

import android.content.Context;
import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;

/**
 * 一种item的Adapter
 * Created by zyz on 2016/5/17.
 */
public class SingleAdapter extends BaseAdapter<Person, SingleHolder> {

    public SingleAdapter(Context context) {
        super(context);
    }

    @Override
    public SingleHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new SingleHolder(parent, R.layout.item_single);
    }

    @Override
    public void bindCustomViewHolder(SingleHolder holder, int position) {
        Person person = getItem(position);
        holder.nameView.setText(person.getName());
        holder.ageView.setText(String.valueOf(person.getAge()));
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }
}
