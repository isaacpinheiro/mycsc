package br.isaac.mycsc.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String messageType;
    private Boolean anonymous;
    private String content;
    private String attachment;
    private Date createdAt;
    private Date updatedAt;
    private CommonUser commonUser;
    private EnterpriseUser enterpriseUser;
    private Product product;

    public Message() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Boolean getAnonymous() {
        return this.anonymous;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setCommonUser(CommonUser enterpriseUser) {
        this.commonUser = commonUser;
    }

    public CommonUser getCommonUser() {
        return this.commonUser;
    }

    public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
        this.enterpriseUser = enterpriseUser;
    }

    public EnterpriseUser getEnterpriseUser() {
        return this.enterpriseUser;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

}
