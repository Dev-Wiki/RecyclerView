package net.devwiki.recyclerview.single;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;

/**
 * 一种item的Adapter
 * Created by zyz on 2016/5/17.
 */
public class SingleAdapter extends BaseAdapter<Person, SingleHolder> {

    private OnSingleItemClickListener clickListener;

    public SingleAdapter(Context context) {
        super(context);
    }

    @Override
    public SingleHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new SingleHolder(parent, R.layout.item_single);
    }

    @Override
    public void bindCustomViewHolder(SingleHolder holder, final int position) {
        Person person = getItem(position);
        holder.nameView.setText(person.getName());
        holder.ageView.setText(String.valueOf(person.getAge()));

        if (clickListener != null) {
            holder.nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onNameClick(position);
                }
            });
            holder.ageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onAgeClick(position);
                }
            });
        }
    }

    public SingleAdapter setClickListener(OnSingleItemClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    public interface OnSingleItemClickListener {
        void onNameClick(int position);

        void onAgeClick(int position);
    }
}
