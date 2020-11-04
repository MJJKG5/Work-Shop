package com.gp.shop.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.gp.shop.common.exception.LogicException;
import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Account;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.AccountMapper;
import com.gp.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询账户
     *
     * @param request 请求
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryAccount(HttpServletRequest request) {
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 查询账户
        Account account = accountMapper.queryById(accountId);
        account.setPassword(null);

        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        return new ResApi<>(map);
    }

    /**
     * 修改账户
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> updateAccount(Account account, HttpServletRequest request) {
        Check.isNull(account.getNickname(), "nickname 参数为空");
        Check.isNull(account.getNickname(), "email 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 设置账户id
        account.setId(accountId);
        // 修改账户
        accountMapper.update(account);

        return new ResApi<>();
    }

    /**
     * 账户修改密码
     *
     * @param account 账户
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> accountPassword(Account account, HttpServletRequest request) {
        // 获取会话
        Session session = new Session(request);
        // 修改密码(旧密码+新密码)
        if (StringUtils.isEmpty(account.getCode())) {
            Check.isNull(account.getOldPassword(), "oldPassword 参数为空");
            Check.isNull(account.getNewPassword(), "newPassword 参数为空");
            // 获取账户id
            Long accountId = session.accountId();
            // 验证账户
            Account result = accountMapper.queryById(accountId);
            if (result != null) {
                // 旧密码加密(MD5)
                String oldPassword = SecureUtil.md5(result.getUsername() + account.getOldPassword());
                // 验证旧密码
                if (!oldPassword.equals(result.getPassword())) {
                    throw new LogicException("旧密码错误");
                }
                // 密码加密(MD5)
                String password = SecureUtil.md5(result.getUsername() + account.getNewPassword());
                // 修改密码
                accountMapper.updateById(accountId, password);
            }
        }

        // 忘记密码(验证码+密码)
        else {
            Check.isNull(account.getUsername(), "username 参数为空");
            Check.isNull(account.getPassword(), "password 参数为空");
            // 检查验证码
            String code = session.code();
            if (!code.equals(account.getCode())) {
                throw new LogicException("验证码错误");
            }
            // 密码加密(MD5)
            String password = SecureUtil.md5(account.getUsername() + account.getPassword());
            // 修改密码
            accountMapper.updateByUserName(account.getUsername(), password);
        }

        return new ResApi<>();
    }
}
