package com.gp.shop.service;

import com.gp.shop.model.Pay;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;

public interface PayService {
    /**
     * 单商品支付
     *
     * @param pay     支付
     * @param request 请求
     * @return
     */
    ResApi<String> pay(Pay pay, HttpServletRequest request);

    /**
     * 购物车商品支付
     *
     * @param pay     支付
     * @param request 请求
     * @return
     */
    ResApi<String> pays(Pay pay, HttpServletRequest request);

}