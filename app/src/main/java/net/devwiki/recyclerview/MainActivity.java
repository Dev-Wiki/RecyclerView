package net.devwiki.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.devwiki.recyclerview.chat.ChatActivity;
import net.devwiki.recyclerview.multi.MultiActivity;
import net.devwiki.recyclerview.single.SingleActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
        }
    }
}
