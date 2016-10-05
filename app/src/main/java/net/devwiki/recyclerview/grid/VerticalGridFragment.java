package net.devwiki.recyclerview.grid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devwiki.recycler.GridDividerDecoration;
import net.devwiki.recycler.HorizontalDividerDecoration;
import net.devwiki.recycler.VerticalDividerDecoration;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerticalGridFragment extends Fragment {

    @BindView(R.id.hori_grid_rv)
    RecyclerView mRecyclerView;

    private VerticalAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    public VerticalGridFragment() {
    }

    public static VerticalGridFragment newInstance() {
        return new VerticalGridFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horizontal_grid, container, false);
        ButterKnife.bind(this, rootView);

        mAdapter = new VerticalAdapter(getContext());
        mAdapter.fillList(MockService.getStringList());
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        GridDividerDecoration dividerDecoration =
                new GridDividerDecoration(getContext(), GridDividerDecoration.VERTICAL_LIST, R.drawable.divider_grid);
        mRecyclerView.addItemDecoration(dividerDecoration);
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
