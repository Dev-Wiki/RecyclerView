package net.devwiki.recyclerview.multi;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;

/**
 * 右侧的ViewHolder
 * Created by zyz on 2016/5/17.
 */
public class RightHolder extends MultiHolder {

    TextView sexView;

    public RightHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);
        sexView = getView(R.id.sex_tv);
    }

    @Override
    public void setData(Person data) {
        super.setData(data);
        if (data.getSex() == Person.FEMALE) {
            sexView.setText("女");
        }
    }
}
