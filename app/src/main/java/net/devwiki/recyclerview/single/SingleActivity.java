package net.devwiki.recyclerview.single;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import net.devwiki.log.DevLog;
import net.devwiki.recycler.DividerDecoration;
import net.devwiki.recycler.ItemClickSupport;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.R;

public class SingleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SingleAdapter singleAdapter;
    private MockService mockService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        mockService = new MockService();
        singleAdapter = new SingleAdapter(this);
        singleAdapter.fillList(mockService.getPersonList());

        recyclerView = (RecyclerView) findViewById(R.id.single_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration decoration = new DividerDecoration(this, DividerDecoration.VERTICAL_LIST);
        Drawable drawable = getResources().getDrawable(R.drawable.divider_single);
        decoration.setDivider(drawable);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(singleAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_single_header, null, false);
        singleAdapter.addHeaderView(view);

        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        DevLog.d("click position:" + position);
                    }
                })
                .setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                        DevLog.d("long click position:" + position);
                        return false;
                    }
                });
    }
}
