package net.devwiki.recyclerview.chat;

/**
 * 聊天消息
 * Created by zyz on 2016/5/18.
 */
public class ChatMsg {

    public static final int TYPE_TEXT = 0;

    public static final int TYPE_IMAGE = 1;

    private String senderName;

    private String createTime;

    private int msgType;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "senderName='" + senderName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
