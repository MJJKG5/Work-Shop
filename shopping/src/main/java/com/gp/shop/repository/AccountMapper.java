package com.gp.shop.repository;

import com.gp.shop.model.Account;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    /**
     * 查询
     *
     * @param page 分页
     * @return
     */
    List<Account> queryByList(@Param("page") Page page);

    /**
     * 查询
     *
     * @param id 账户id
     * @return
     */
    Account queryById(Long id);

    /**
     * 查询
     *
     * @param username 用户名
     * @return
     */
    Account queryByUsername(String username);

    /**
     * 添加
     *
     * @param account 账户
     */
    void add(@Param("account") Account account);

    /**
     * 修改
     *
     * @param account 账户
     */
    void update(@Param("account") Account account);

    /**
     * 修改
     *
     * @param id       账户id
     * @param password 密码
     */
    void updateById(@Param("id") Long id, @Param("password") String password);

    /**
     * 修改
     *
     * @param username 用户名
     * @param password 密码
     */
    void updateByUserName(@Param("username") String username, @Param("password") String password);

    /**
     * 删除
     *
     * @param id 账户id
     */
    void delete(Long id);

    /**
     * 查询数量
     *
     * @return
     */
    Long count();
}
