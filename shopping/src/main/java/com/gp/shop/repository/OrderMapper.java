package com.gp.shop.repository;

import com.gp.shop.model.Order;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    /**
     * 查询
     *
     * @param accountId 账户id
     * @param page      分页
     * @return
     */
    List<Order> queryByList(@Param("accountId") Long accountId, @Param("page") Page page);

    /**
     * 添加
     *
     * @param order 订单
     */
    void add(@Param("order") Order order);

    /**
     * 添加
     *
     * @param orders 订单集合
     */
    void adds(@Param("orders") List<Order> orders);

    /**
     * 修改
     *
     * @param id        订单id
     * @param isReviews 是否已评论
     */
    void updateById(@Param("id") Long id, @Param("isReviews") String isReviews);

    /**
     * 删除
     *
     * @param ids 订单id集合
     */
    void delete(@Param("ids") List<Long> ids);

    /**
     * 查询数量
     *
     * @param accountId 账户id
     * @return
     */
    Long count(Long accountId);
}
