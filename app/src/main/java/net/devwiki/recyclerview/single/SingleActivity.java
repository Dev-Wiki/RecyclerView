package net.devwiki.recyclerview.single;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import net.devwiki.recycler.DividerDecoration;
import net.devwiki.recycler.RecyclerItemClickListener;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.Person;
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Person person = singleAdapter.getItem(position);
                        Toast.makeText(SingleActivity.this, "click:" + person,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Person person = singleAdapter.getItem(position);
                        Toast.makeText(SingleActivity.this, "Long Click:" + person,
                                Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
