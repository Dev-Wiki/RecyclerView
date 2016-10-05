package net.devwiki.recyclerview.divider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import net.devwiki.recycler.DividerDecoration;
import net.devwiki.recycler.GridDividerDecoration;
import net.devwiki.recycler.HorizontalDividerDecoration;
import net.devwiki.recycler.VerticalDividerDecoration;
import net.devwiki.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DividerActivity extends AppCompatActivity {

    @BindView(R.id.divider_rv)
    RecyclerView mDividerRv;

    private RecyclerView.LayoutManager mLayoutManager;
    private DividerAdapter mAdapter;
    private DividerDecoration mDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divider);
        ButterKnife.bind(this);

        mAdapter = new DividerAdapter(this);
        mLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mDividerRv.setLayoutManager(mLayoutManager);
        mDividerRv.setAdapter(mAdapter);

//        HorizontalDividerDecoration horizontalDividerDecoration =
//                new HorizontalDividerDecoration(this, R.drawable.divider_drawable);
//        VerticalDividerDecoration verticalDividerDecoration =
//                new VerticalDividerDecoration(this, R.drawable.divider_drawable);
//        mDividerRv.addItemDecoration(horizontalDividerDecoration);
//        mDividerRv.addItemDecoration(verticalDividerDecoration);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        mAdapter.fillList(list);
    }

    @OnClick({R.id.hori_btn, R.id.vert_btn, R.id.grid_h_btn, R.id.grid_v_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hori_btn:
                if (mDecoration != null) {
                    mDividerRv.removeItemDecoration(mDecoration);
                }
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mDividerRv.setLayoutManager(mLayoutManager);
                mDecoration = new HorizontalDividerDecoration(this, R.drawable.divider_drawable);
                mDividerRv.addItemDecoration(mDecoration);
                break;
            case R.id.vert_btn:
                if (mDecoration != null) {
                    mDividerRv.removeItemDecoration(mDecoration);
                }
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                mDividerRv.setLayoutManager(mLayoutManager);
                mDecoration = new VerticalDividerDecoration(this, R.drawable.divider_drawable);
                mDividerRv.addItemDecoration(mDecoration);
                break;
            case R.id.grid_h_btn:
                if (mDecoration != null) {
                    mDividerRv.removeItemDecoration(mDecoration);
                }
                mLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false);
                mDividerRv.setLayoutManager(mLayoutManager);
                mDecoration = new GridDividerDecoration(this, GridDividerDecoration.HORIZONTAL_LIST,
                        R.drawable.divider_drawable);
                mDividerRv.addItemDecoration(mDecoration);
                break;
            case R.id.grid_v_btn:
                if (mDecoration != null) {
                    mDividerRv.removeItemDecoration(mDecoration);
                }
                mLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
                mDividerRv.setLayoutManager(mLayoutManager);
                mDecoration = new GridDividerDecoration(this, GridDividerDecoration.VERTICAL_LIST,
                        R.drawable.divider_drawable);
                mDividerRv.addItemDecoration(mDecoration);
                break;
        }
    }
}
