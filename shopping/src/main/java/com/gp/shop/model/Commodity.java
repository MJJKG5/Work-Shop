package com.gp.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Commodity implements Serializable {
    private static final long serialVersionUID = 8954339963573757971L;
    /**
     * 商店id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态（0：下架、1：上架）
     */
    private String status;
    /**
     * 库存
     */
    private Integer inventory;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 图片
     */
    private String url;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 简介
     */
    private String intro;
    /**
     * 商店id
     */
    private Long storeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
