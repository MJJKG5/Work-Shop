package com.gp.shop.service;

import com.gp.shop.model.Account;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AccountService {
    /**
     * 查询账户
     *
     * @param request 请求
     * @return
     */
    ResApi<Map<String, Object>> queryAccount(HttpServletRequest request);

    /**
     * 修改账户
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    ResApi<String> updateAccount(Account account, HttpServletRequest request);

    /**
     * 账户修改密码
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    ResApi<String> accountPassword(Account account, HttpServletRequest request);
}
