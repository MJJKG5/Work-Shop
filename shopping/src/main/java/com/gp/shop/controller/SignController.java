package com.gp.shop.controller;

import com.gp.shop.model.Account;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api")
public class SignController {
    @Autowired
    private SignService signService;

    /**
     * 注册
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public ResApi<String> signUp(@RequestBody Account account, HttpServletRequest request) {
        return signService.signUp(account, request);
    }

    /**
     * 登录
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public ResApi<Map<String, Object>> signIn(@RequestBody Account account, HttpServletRequest request) {
        return signService.signIn(account, request);
    }

    /**
     * 注销
     *
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "signOut", method = RequestMethod.GET)
    public ResApi<String> signOut(HttpServletRequest request) {
        return signService.signOut(request);
    }
}
