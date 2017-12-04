package br.isaac.mycsc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chat")
public class Chat implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_on", nullable = false)
    private Date sentOn;

    @ManyToOne
    @JoinColumn(name = "from_user_id", referencedColumnName = "id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id", referencedColumnName = "id")
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
