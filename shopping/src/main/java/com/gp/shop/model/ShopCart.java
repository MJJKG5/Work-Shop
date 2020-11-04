package com.gp.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopCart implements Serializable {
    private static final long serialVersionUID = -4172471573514949000L;
    /**
     * 购物车id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long commId;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 商品图片
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommId() {
        return commId;
    }

    public void setCommId(Long commId) {
        this.commId = commId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
