package net.devwiki.recyclerview.multi;

/**
 * Created by zyz on 2016/5/18.
 */
public class ChatMsg {

    private String senderName;

    private String createTime;

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

    @Override
    public String toString() {
        return "ChatMsg{" +
                "senderName='" + senderName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
