package br.isaac.mycsc.model;

import java.io.Serializable;
import java.util.Date;

public class EnterpriseUser implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String tradeName;
    private String regCode;
    private String country;
    private String img;
    private Date createdAt;
    private Date updatedAt;
    private User user;

    public EnterpriseUser() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getTradeName() {
        return this.tradeName;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getRegCode() {
        return this.regCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return this.img;
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
