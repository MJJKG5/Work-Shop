package com.gp.shop.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.gp.shop.common.exception.LogicException;
import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Account;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.AccountMapper;
import com.gp.shop.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service("signService")
public class SignServiceImpl implements SignService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 注册
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> signUp(Account account, HttpServletRequest request) {
        Check.isNull(account.getNickname(), "nickname 参数为空");
        Check.isNull(account.getUsername(), "username 参数为空");
        Check.isNull(account.getPassword(), "password 参数为空");
        Check.isNull(account.getEmail(), "email 参数为空");
        Check.isNull(account.getCode(), "code 参数为空");

        Session session = new Session(request);
        // 获取验证码
        String code = session.code();
        // 检查验证码
        if (!code.equals(account.getCode())) {
            throw new LogicException("验证码错误");
        }

        // 验证账户
        Account result = accountMapper.queryByUsername(account.getUsername());
        if (result != null) {
            throw new LogicException("用户名已被注册");
        }
        // 密码加密(MD5)
        String password = SecureUtil.md5(account.getUsername() + account.getPassword());
        // 设置密码
        account.setPassword(password);
        // 添加账户
        accountMapper.add(account);

        return new ResApi<>();
    }

    /**
     * 登录
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> signIn(Account account, HttpServletRequest request) {
        Check.isNull(account.getUsername(), "username 参数为空");
        Check.isNull(account.getPassword(), "password 参数为空");
        // 验证账户
        Account result = accountMapper.queryByUsername(account.getUsername());
        if (result == null) {
            throw new LogicException("用户名或密码错误");
        }
        // 密码加密(MD5)
        String password = SecureUtil.md5(account.getUsername() + account.getPassword());
        if (!password.equals(result.getPassword())) {
            throw new LogicException("用户名或密码错误");
        }
        // 存储账户id
        new Session(request).set(request.getSession().getId(), result.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("id", result.getId());
        map.put("nickname", result.getNickname());
        return new ResApi<>(map);
    }

    /**
     * 注销
     *
     * @param request 请求
     * @return
     */
    @Override
    public ResApi<String> signOut(HttpServletRequest request) {
        // 注销
        new Session(request).del(request.getSession().getId());

        return new ResApi<>();
    }
}
