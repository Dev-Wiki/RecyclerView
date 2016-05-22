package net.devwiki.recyclerview.chat;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.recycler.DividerItemDecoration;
import net.devwiki.recycler.listener.OnItemClickListener;
import net.devwiki.recyclerview.MockService;
import net.devwiki.recyclerview.R;

public class ChatActivity extends AppCompatActivity {

    private SwipeRefreshLayout chatSrl;
    private RecyclerView chatRv;
    private ChatAdapter chatAdapter;
    private MockService mockService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initData();

        initView();
    }

    private void initData() {
        mockService = new MockService();
        chatAdapter = new ChatAdapter(new OnItemClickListener<ChatHolder>() {
            @Override
            public void onItemClick(ChatHolder holder) {

            }
        });
        chatAdapter.fillList(mockService.getChatMsgList());
    }

    private void initView() {
        chatSrl = (SwipeRefreshLayout) findViewById(R.id.chat_srv);
        chatSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMoreData();
            }
        });
        chatSrl.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLACK, Color.BLUE);

        chatRv = (RecyclerView) findViewById(R.id.chat_rv);
        chatRv.setLayoutManager(new LinearLayoutManager(this));
        chatRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        chatRv.setAdapter(chatAdapter);
    }

    private void loadMoreData() {
        chatAdapter.appendList(mockService.getChatMsgList());
        chatAdapter.notifyDataSetChanged();
        chatSrl.setRefreshing(false);
    }
}
