package com.gp.shop.controller;

import com.gp.shop.model.Pay;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api")
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * 单商品支付
     *
     * @param pay     支付
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public ResApi<String> pay(@RequestBody Pay pay, HttpServletRequest request) {
        return payService.pay(pay, request);
    }

    /**
     * 购物车商品支付
     *
     * @param pay     支付
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "pays", method = RequestMethod.POST)
    public ResApi<String> pays(@RequestBody Pay pay, HttpServletRequest request) {
        return payService.pays(pay, request);
    }
}