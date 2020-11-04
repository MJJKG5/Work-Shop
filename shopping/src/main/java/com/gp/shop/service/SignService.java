package com.gp.shop.service;

import com.gp.shop.model.Account;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SignService {
    /**
     * 注册
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    ResApi<String> signUp(Account account, HttpServletRequest request);

    /**
     * 登录
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    ResApi<Map<String, Object>> signIn(Account account, HttpServletRequest request);

    /**
     * 注销
     *
     * @param request 请求
     * @return
     */
    ResApi<String> signOut(HttpServletRequest request);
}
