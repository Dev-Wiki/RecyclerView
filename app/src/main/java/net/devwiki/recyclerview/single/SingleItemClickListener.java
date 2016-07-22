package net.devwiki.recyclerview.single;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 点击事件
 * Created by DevWiki on 2016/7/16.
 */

public class SingleItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener clickListener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener {
        void onNameClick(View view, int position);

        void onAgeClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public SingleItemClickListener(final RecyclerView recyclerView,
                                   OnItemClickListener listener) {
        this.clickListener = listener;
        gestureDetector = new GestureDetector(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && clickListener != null) {
                            clickListener.onItemLongClick(childView,
                                    recyclerView.getChildAdapterPosition(childView));
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            RecyclerView.ViewHolder viewHolder = rv.findContainingViewHolder(childView);
            if (viewHolder != null && viewHolder instanceof SingleHolder) {
                SingleHolder singleHolder = (SingleHolder) viewHolder;
                if (e.getX() > singleHolder.nameView.getX()
                        && e.getX() < singleHolder.nameView.getX() + singleHolder.nameView.getWidth()
                        && e.getY() > singleHolder.nameView.getY()
                        && e.getY() < singleHolder.nameView.getY() + singleHolder.nameView.getHeight()) {
                    clickListener.onNameClick(singleHolder.nameView, rv.getChildAdapterPosition(childView));
                }
                if (e.getX() > singleHolder.ageView.getX()
                        && e.getX() < singleHolder.ageView.getX() + singleHolder.ageView.getWidth()
                        && e.getY() > singleHolder.ageView.getY()
                        && e.getY() < singleHolder.ageView.getY() + singleHolder.ageView.getHeight()) {
                    clickListener.onAgeClick(singleHolder.ageView, rv.getChildAdapterPosition(childView));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
