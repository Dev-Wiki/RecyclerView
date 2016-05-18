package net.devwiki.recyclerview.single;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import net.devwiki.recycler.DividerItemDecoration;
import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SingleAdapter singleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        Random random = new Random();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setName("name-" + i);
            person.setAge(random.nextInt(30));
            person.setSex(i%2);
            list.add(person);
        }

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
                Person data = singleAdapter.getData(holder);
                Toast.makeText(SingleActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        singleAdapter.fillList(list);

        recyclerView = (RecyclerView) findViewById(R.id.single_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(singleAdapter);
    }
}
