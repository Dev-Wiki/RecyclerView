package net.devwiki.recyclerview.footer;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.devwiki.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FooterActivity extends AppCompatActivity {

    @BindView(R.id.footer_rv)
    RecyclerView footerRv;
    @BindView(R.id.footer_srl)
    SwipeRefreshLayout footerSrl;

    private FooterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        footerRv.setLayoutManager(layoutManager);
        View footerView = getLayoutInflater().inflate(R.layout.item_footer_footer, null);
        adapter.addFooterView(footerView);
        footerRv.setAdapter(adapter);

        footerSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
        Observable.create(
                new Observable.OnSubscribe<List<String>>() {
                    @Override
                    public void call(Subscriber<? super List<String>> subscriber) {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 50; i++) {
                            list.add("str-" + i);
                        }
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        if (footerSrl.isRefreshing()) {
                            footerSrl.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<String> list) {
                        adapter.appendList(list);
                    }
                });
    }
}
