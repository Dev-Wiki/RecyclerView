package net.devwiki.recyclerview.single;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import net.devwiki.log.DevLog;
import net.devwiki.recycler.DividerDecoration;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.R;

public class SingleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SingleAdapter singleAdapter;
    private MockService mockService;

    private SingleItemClickListener touchListener;
    private SingleAdapter.OnSingleItemClickListener adapterListener;
    private SingleItemClickSupport supportListener;

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

        String type = getIntent().getStringExtra("type");
        if ("adapter".equals(type)) {
            setClickListenerWithAdapter();
        } else if ("touch".equals(type)) {
            setClickListenerWithTouch();
        } else {
            setClickListenerWithSupport();
        }
    }

    private void setClickListenerWithTouch() {
        recyclerView.addOnItemTouchListener(new SingleItemClickListener(recyclerView,
                new SingleItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        DevLog.i("touch click name:" + position);
                        Toast.makeText(SingleActivity.this, "touch click:" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        DevLog.i("touch long click:" + position);
                        Toast.makeText(SingleActivity.this, "touch long click:" + position, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void setClickListenerWithAdapter() {
        singleAdapter.setClickListener(new SingleAdapter.OnSingleItemClickListener() {
            @Override
            public void onNameClick(int position) {
                DevLog.i("adapter click name:" + position);
                Toast.makeText(SingleActivity.this, "adapter click name:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAgeClick(int position) {
                DevLog.i("adapter click age:" + position);
                Toast.makeText(SingleActivity.this, "adapter click name:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setClickListenerWithSupport() {
        SingleItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new SingleItemClickSupport.OnItemClickListener() {

                    @Override
                    public void onNameClicked(RecyclerView recyclerView, int position, View view) {
                        DevLog.i("support name click:" + position);
                        Toast.makeText(SingleActivity.this, "support click name:" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAgeClicked(RecyclerView recyclerView, int position, View view) {
                        DevLog.i("support age click:" + position);
                        Toast.makeText(SingleActivity.this, "support click name:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
