package net.devwiki.recyclerview.grid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.recycler.HorizontalDividerDecoration;
import net.devwiki.recycler.VerticalDividerDecoration;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.R;
import net.devwiki.recyclerview.divider.DividerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalGridFragment extends Fragment {

    @BindView(R.id.hori_grid_rv)
    RecyclerView mRecyclerView;

    private HorizontalAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    public HorizontalGridFragment() {
    }

    public static HorizontalGridFragment newInstance() {
        return new HorizontalGridFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horizontal_grid, container, false);
        ButterKnife.bind(this, rootView);

        mAdapter = new HorizontalAdapter(getContext());
        mAdapter.fillList(MockService.getStringList());
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        VerticalDividerDecoration verticalDividerDecoration =
                new VerticalDividerDecoration(getContext(), R.drawable.divider_grid_vertical);
        mRecyclerView.addItemDecoration(verticalDividerDecoration);
        HorizontalDividerDecoration horizontalDividerDecoration =
                new HorizontalDividerDecoration(getContext(), R.drawable.divider_grid_horizontal);
        mRecyclerView.addItemDecoration(horizontalDividerDecoration);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
