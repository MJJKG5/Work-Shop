package com.gp.shop.repository;

import com.gp.shop.model.ShopCart;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCartMapper {
    /**
     * 查询
     *
     * @param accountId 账户id
     * @param page      分页
     * @return
     */
    List<ShopCart> queryByList(@Param("accountId") Long accountId, @Param("page") Page page);

    /**
     * 查询
     *
     * @param accountId 账户id
     * @return
     */
    List<ShopCart> queryByAccountId(@Param("accountId") Long accountId);

    /**
     * 查询
     *
     * @param accountId 账户id
     * @param commId    商品id
     * @return
     */
    ShopCart queryByAccountIdAndCommId(@Param("accountId") Long accountId, @Param("commId") Long commId);

    /**
     * 添加
     *
     * @param shopCart 购物车
     */
    void add(@Param("shopCart") ShopCart shopCart);

    /**
     * 修改
     *
     * @param shopCart 购物车
     */
    void update(@Param("shopCart") ShopCart shopCart);

    /**
     * 删除
     *
     * @param id 购物车id
     */
    void delete(Long id);

    /**
     * 删除
     *
     * @param accountId 账户id
     * @param commId    商品id
     */
    void deleteByAccountIdAndCommId(@Param("accountId") Long accountId, @Param("commId") Long commId);

    /**
     * 删除
     *
     * @param commIds 商品id集合
     */
    void deleteByCommIds(@Param("commIds") List<Long> commIds);

    /**
     * 查询数量
     *
     * @param accountId 账户id
     * @return
     */
    Long count(Long accountId);
}
