package net.devwiki.recyclerview.chat;

import net.devwiki.recyclerview.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by zyz on 2016/5/18.
 */
public class MockService {

    private int[] drawableRes = new int[]{R.drawable.ic_000, R.drawable.ic_001, R.drawable.ic_002, R.drawable.ic_003,
            R.drawable.ic_004, R.drawable.ic_005, R.drawable.ic_006, R.drawable.ic_007,
            R.drawable.ic_008, R.drawable.ic_009};

    public MockService(){}

    public List<ChatMsg> getChatMsgList() {
        List<ChatMsg> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int value = random.nextInt(10);
            if (value < 4) {
                TextMsg textMsg = new TextMsg();
                textMsg.setText("text-" + i);
                textMsg.setSenderName("Bob");
                textMsg.setMsgType(ChatMsg.TYPE_TEXT);
                textMsg.setCreateTime(getShowTime(System.currentTimeMillis() - i*value*1000));
                list.add(textMsg);
            } else {
                ImageMsg imageMsg = new ImageMsg();
                imageMsg.setSenderName("Mary");
                imageMsg.setMsgType(ChatMsg.TYPE_IMAGE);
                imageMsg.setResId(drawableRes[i/2]);
                imageMsg.setCreateTime(getShowTime(System.currentTimeMillis() + i*value*1000));
                list.add(imageMsg);
            }
        }
        return list;
    }

    private String getShowTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date(time));
    }
}
