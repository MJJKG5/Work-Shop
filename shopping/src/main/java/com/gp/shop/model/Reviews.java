package com.gp.shop.model;

import java.io.Serializable;

public class Reviews implements Serializable {
    private static final long serialVersionUID = 6011933487458123717L;
    /**
     * 商店id
     */
    private Long id;
    /**
     * 信息
     */
    private String message;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 商品id
     */
    private Long commId;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 账户id
     */
    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCommId() {
        return commId;
    }

    public void setCommId(Long commId) {
        this.commId = commId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
