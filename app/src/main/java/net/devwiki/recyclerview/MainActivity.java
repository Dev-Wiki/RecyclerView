package net.devwiki.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.devwiki.recyclerview.chat.ChatActivity;
import net.devwiki.recyclerview.divider.DividerActivity;
import net.devwiki.recyclerview.grid.GridActivity;
import net.devwiki.recyclerview.multi.MultiActivity;
import net.devwiki.recyclerview.scroll.ScrollActivity;
import net.devwiki.recyclerview.single.SingleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.single_adapter, R.id.single_touch, R.id.single_support, R.id.chat_btn, R.id.multi_btn,
            R.id.scroll_btn, R.id.divider_btn, R.id.grid_btn})
    public void onClick(View view) {
        Intent intent = new Intent(this, SingleActivity.class);
        switch (view.getId()) {
            case R.id.single_adapter:
                intent.putExtra("type", "adapter");
                startActivity(intent);
                break;
            case R.id.single_touch:
                intent.putExtra("type", "touch");
                startActivity(intent);
                break;
            case R.id.single_support:
                intent.putExtra("type", "support");
                startActivity(intent);
                break;
            case R.id.chat_btn:
                startActivity(new Intent(this, ChatActivity.class));
                break;
            case R.id.multi_btn:
                startActivity(new Intent(this, MultiActivity.class));
                break;
            case R.id.scroll_btn:
                startActivity(new Intent(this, ScrollActivity.class));
                break;
            case R.id.divider_btn:
                startActivity(new Intent(this, DividerActivity.class));
                break;
            case R.id.grid_btn:
                startActivity(new Intent(this, GridActivity.class));
                break;
        }
    }
}
