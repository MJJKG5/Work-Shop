package com.gp.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    private static final long serialVersionUID = -5624236856883192052L;
    /**
     * 订单id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long commId;
    /**
     * 状态（0：成功；1：失败）
     */
    private String status;
    /**
     * 是否已评论（0：未评论、1：已评论）
     */
    private String isReviews;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 商品名
     */
    private String name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsReviews() {
        return isReviews;
    }

    public void setIsReviews(String isReviews) {
        this.isReviews = isReviews;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
