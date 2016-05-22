package net.devwiki.recyclerview.chat;

/**
 * 表示一条文本消息
 * Created by zyz on 2016/5/18.
 */
public class TextMsg extends ChatMsg {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
