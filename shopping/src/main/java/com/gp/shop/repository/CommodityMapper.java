package com.gp.shop.repository;

import com.gp.shop.model.Commodity;
import com.gp.shop.model.ShopCart;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityMapper {
    /**
     * 查询
     *
     * @param storeId 商店id
     * @param page    分页
     * @return
     */
    List<Commodity> queryByList(@Param("storeId") Long storeId, @Param("page") Page page);

    /**
     * 查询
     *
     * @param commIds 商品id集合
     * @return
     */
    List<Commodity> queryByIds(@Param("ids") List<Long> commIds);

    /**
     * 查询
     *
     * @param id 商品id
     * @return
     */
    Commodity queryById(Long id);

    /**
     * 添加
     *
     * @param commodity 商品
     */
    void add(@Param("commodity") Commodity commodity);

    /**
     * 修改
     *
     * @param commodity 商品
     */
    void update(@Param("commodity") Commodity commodity);

    /**
     * 删除
     *
     * @param id 商品id
     */
    void delete(Long id);

    /**
     * 查询数量
     *
     * @param storeId 商店id
     * @return
     */
    Long count(Long storeId);
}
