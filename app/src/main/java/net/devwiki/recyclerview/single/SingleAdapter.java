package net.devwiki.recyclerview.single;

import android.view.View;
import android.view.ViewGroup;

import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;
import net.devwiki.recyclerview.base.BaseAdapter;

/**
 * 一种item的Adapter
 * Created by zyz on 2016/5/17.
 */
public class SingleAdapter extends BaseAdapter<Person, SingleHolder> {

    public SingleAdapter(SingleItemClickListener listener) {
        super(listener);
        super.listener = listener;
    }

    @Override
    public SingleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SingleHolder(parent, R.layout.item_single);
    }

    @Override
    public void onBindViewHolder(final SingleHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SingleItemClickListener) listener).onNameClick(getData(holder).getName());
            }
        });

        holder.ageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SingleItemClickListener) listener).onAgeClick(getData(holder).getAge());
            }
        });
    }

    public interface SingleItemClickListener extends OnItemClickListener<SingleHolder>{

        void onNameClick(String name);

        void onAgeClick(int age);
    }
}
