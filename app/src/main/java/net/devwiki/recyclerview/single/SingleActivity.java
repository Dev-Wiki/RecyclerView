package net.devwiki.recyclerview.single;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import net.devwiki.recycler.DividerItemDecoration;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.Person;
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
        singleAdapter = new SingleAdapter(new SingleAdapter.SingleItemClickListener() {
            @Override
            public void onNameClick(String name) {
                Toast.makeText(SingleActivity.this, name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAgeClick(int age) {
                Toast.makeText(SingleActivity.this, String.valueOf(age), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(SingleHolder holder) {
                Person data = singleAdapter.getItem(holder);
                Toast.makeText(SingleActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        singleAdapter.fillList(mockService.getPersonList());

        recyclerView = (RecyclerView) findViewById(R.id.single_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(singleAdapter);
    }
}
