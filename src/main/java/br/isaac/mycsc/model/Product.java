package br.isaac.mycsc.model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String brand;
    private String name;
    private String description;
    private String img;
    private Date createdAt;
    private Date updatedAt;
    private EnterpriseUser enterpriseUser;

    public Product() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
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

    public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
        this.enterpriseUser = enterpriseUser;
    }

    public EnterpriseUser getEnterpriseUser() {
        return this.enterpriseUser;
    }

}
