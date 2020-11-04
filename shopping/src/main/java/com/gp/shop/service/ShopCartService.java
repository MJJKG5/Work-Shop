package com.gp.shop.service;

import com.gp.shop.model.ShopCart;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ShopCartService {
    /**
     * 查询购物车
     *
     * @param request  请求
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    ResApi<Map<String, Object>> queryShopCart(HttpServletRequest request, Integer pageNo, Integer pageSize);

    /**
     * 添加购物车
     *
     * @param shopCart 购物车
     * @param request  请求
     * @return
     */
    ResApi<String> addShopCart(ShopCart shopCart, HttpServletRequest request);

    /**
     * 修改购物车
     *
     * @param shopCart 购物车
     * @param request  请求
     * @return
     */
    ResApi<String> updateShopCart(ShopCart shopCart, HttpServletRequest request);

    /**
     * 删除购物车
     *
     * @param commId  商品
     * @param request 请求
     * @return
     */
    ResApi<String> deleteShopCart(Long commId, HttpServletRequest request);
}
