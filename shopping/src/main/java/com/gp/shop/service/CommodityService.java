package com.gp.shop.service;

import com.gp.shop.model.Commodity;
import com.gp.shop.model.entity.ResApi;

import java.util.Map;

public interface CommodityService {
    /**
     * 查询商品
     *
     * @param storeId  商店id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    ResApi<Map<String, Object>> queryCommodity(Long storeId, Integer pageNo, Integer pageSize);

    /**
     * 查询商品
     *
     * @param id 商品id
     * @return
     */
    ResApi<Map<String, Object>> queryCommodity(Long id);

    /**
     * 添加商品
     *
     * @param commodity 商品
     * @return
     */
    ResApi<String> addCommodity(Commodity commodity);

    /**
     * 修改商品
     *
     * @param commodity 商品
     * @return
     */
    ResApi<String> updateCommodity(Commodity commodity);

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return
     */
    ResApi<String> deleteCommodity(Long id);
}
