package net.devwiki.recyclerview.single;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recycler.BaseHolder;
import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;

/**
 * 一种View的Holder
 * Created by zyz on 2016/5/17.
 */
public class SingleHolder extends BaseHolder<Person> {

    TextView nameView;
    TextView ageView;

    public SingleHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);

        nameView = getView(R.id.name_tv);
        ageView = getView(R.id.age_tv);
    }

    @Override
    public void setData(Person data) {
        nameView.setText(data.getName());
        ageView.setText(String.valueOf(data.getAge()));
    }
}
