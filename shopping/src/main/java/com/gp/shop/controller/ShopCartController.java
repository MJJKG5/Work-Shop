package com.gp.shop.controller;

import com.gp.shop.model.ShopCart;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ShopCartController {
    @Autowired
    private ShopCartService shopCartService;

    /**
     * 查询购物车
     *
     * @param request  请求
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "shopCart", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryShopCart(HttpServletRequest request,
                                                     @RequestParam Integer pageNo,
                                                     @RequestParam Integer pageSize) {
        return shopCartService.queryShopCart(request, pageNo, pageSize);
    }

    /**
     * 添加购物车
     *
     * @param shopCart 购物车
     * @param request  请求
     * @return
     */
    @RequestMapping(value = "shopCart", method = RequestMethod.POST)
    public ResApi<String> addShopCart(@RequestBody ShopCart shopCart, HttpServletRequest request) {
        return shopCartService.addShopCart(shopCart, request);
    }

    /**
     * 修改购物车
     *
     * @param shopCart 购物车
     * @param request  请求
     * @return
     */
    @RequestMapping(value = "shopCart", method = RequestMethod.PUT)
    public ResApi<String> updateShopCart(@RequestBody ShopCart shopCart, HttpServletRequest request) {
        return shopCartService.updateShopCart(shopCart, request);
    }

    /**
     * 删除购物车
     *
     * @param commId  商品id
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "shopCart", method = RequestMethod.DELETE)
    public ResApi<String> deleteShopCart(@RequestParam Long commId, HttpServletRequest request) {
        return shopCartService.deleteShopCart(commId, request);
    }
}
