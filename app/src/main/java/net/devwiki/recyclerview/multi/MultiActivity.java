package net.devwiki.recyclerview.multi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.recyclerview.Person;
import net.devwiki.recyclerview.R;
import net.devwiki.recyclerview.base.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MultiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        Random random = new Random();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setName("name-" + i);
            person.setAge(random.nextInt(30));
            person.setSex(i%2);
            list.add(person);
        }

        adapter = new MultiAdapter();
        adapter.fillList(list);

        recyclerView = (RecyclerView) findViewById(R.id.multi_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }
}
