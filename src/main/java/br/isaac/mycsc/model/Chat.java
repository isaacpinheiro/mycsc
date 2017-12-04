package br.isaac.mycsc.model;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String content;
    private Date sentOn;
    private User fromUser;
    private User toUser;

    public Chat() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public Date getSentOn() {
        return this.sentOn;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getFromUser() {
        return this.fromUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getToUser() {
        return this.toUser;
    }

}
