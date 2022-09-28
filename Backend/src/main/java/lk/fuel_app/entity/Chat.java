package lk.fuel_app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chat {
    @Id
    private  String chatId;
    private String chaterName;
    private String msg;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChaterName() {
        return chaterName;
    }

    public void setChaterName(String chaterName) {
        this.chaterName = chaterName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
