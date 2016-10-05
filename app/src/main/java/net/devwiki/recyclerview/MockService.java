package net.devwiki.recyclerview;

import net.devwiki.recyclerview.R;
import net.devwiki.recyclerview.chat.ChatMsg;
import net.devwiki.recyclerview.chat.ImageMsg;
import net.devwiki.recyclerview.chat.TextMsg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 提供数据
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
        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(10);
            if (value < 4) {
                TextMsg textMsg = new TextMsg();
                textMsg.setText("text-" + i);
                textMsg.setSenderName("Bob");
                textMsg.setMsgType(ChatMsg.TYPE_TEXT);
                textMsg.setCreateTime(getShowTime(System.currentTimeMillis() + i*1000));
                list.add(textMsg);
            } else {
                ImageMsg imageMsg = new ImageMsg();
                imageMsg.setSenderName("Mary");
                imageMsg.setMsgType(ChatMsg.TYPE_IMAGE);
                imageMsg.setResId(drawableRes[i%3]);
                imageMsg.setCreateTime(getShowTime(System.currentTimeMillis() + i*1000));
                list.add(imageMsg);
            }
        }
        return list;
    }

    private String getShowTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date(time));
    }

    public List<Person> getPersonList() {
        Random random = new Random();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setName("name-" + i);
            person.setAge(random.nextInt(30));
            person.setSex(i%2);
            list.add(person);
        }
        return list;
    }

    public static List<String> getStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
