package com.gp.shop.model;

import java.io.Serializable;
import java.util.List;

public class Pay implements Serializable {
    private static final long serialVersionUID = -665170824087275762L;
    /**
     * 商品id
     */
    private Long commId;
    /**
     * 商品id集合
     */
    private List<Long> commIds;

    public Long getCommId() {
        return commId;
    }

    public void setCommId(Long commId) {
        this.commId = commId;
    }

    public List<Long> getCommIds() {
        return commIds;
    }

    public void setCommIds(List<Long> commIds) {
        this.commIds = commIds;
    }
}