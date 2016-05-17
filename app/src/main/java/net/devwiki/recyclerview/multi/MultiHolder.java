package net.devwiki.recyclerview.multi;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import net.devwiki.recyclerview.base.BaseHolder;
import net.devwiki.recyclerview.Person;

/**
 * Created by zyz on 2016/5/17.
 */
public class MultiHolder extends BaseHolder<Person> {


    public MultiHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);
    }

    @Override
    public void setData(Person data) {

    }
}
