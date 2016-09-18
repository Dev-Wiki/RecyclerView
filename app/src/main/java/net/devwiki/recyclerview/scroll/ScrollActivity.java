package net.devwiki.recyclerview.scroll;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import net.devwiki.log.DevLog;
import net.devwiki.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScrollActivity extends AppCompatActivity {

    @BindView(R.id.scroll_rv)
    RecyclerView scrollRv;

    private ScrollAdapter adapter;
    private Button footerBtn1;
    private Button footerBtn2;
    private Button footerBtn3;

    private GestureDetectorCompat detectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);

        initView();
        loadData();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        scrollRv.setLayoutManager(layoutManager);
        adapter = new ScrollAdapter(this);
        View footerView = getLayoutInflater().inflate(R.layout.layout_scroll_footer, null, false);
        footerBtn1 = (Button) footerView.findViewById(R.id.footer_btn1);
        footerBtn2 = (Button) footerView.findViewById(R.id.footer_btn2);
        footerBtn3 = (Button) footerView.findViewById(R.id.footer_btn3);
        adapter.addFooterView(footerView);
        scrollRv.setAdapter(adapter);

        detectorCompat = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                DevLog.i("distanceY:" + distanceY);
                if (distanceY > 0 && !scrollRv.canScrollVertically(1)) {
                    footerBtn2.setVisibility(View.VISIBLE);
                    footerBtn3.setVisibility(View.VISIBLE);
                } else {
                    footerBtn2.setVisibility(View.GONE);
                    footerBtn3.setVisibility(View.GONE);
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
    }

    private void loadData() {
        Observable.create(
                new Observable.OnSubscribe<List<String>>() {
                    @Override
                    public void call(Subscriber<? super List<String>> subscriber) {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 50; i++) {
                            list.add("data-" + i);
                        }
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        DevLog.e(e.getMessage());
                    }

                    @Override
                    public void onNext(List<String> list) {
                        adapter.fillList(list);
                    }
                });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        detectorCompat.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
