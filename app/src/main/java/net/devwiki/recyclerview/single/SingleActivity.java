package net.devwiki.recyclerview.single;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.recycler.DividerItemDecoration;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.R;

public class SingleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(singleAdapter);
    }
}
