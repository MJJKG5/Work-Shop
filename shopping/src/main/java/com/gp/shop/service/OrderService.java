package com.gp.shop.service;

import com.gp.shop.model.Order;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 查询订单
     *
     * @param request  请求
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    ResApi<Map<String, Object>> queryOrder(HttpServletRequest request, Integer pageNo, Integer pageSize);

    /**
     * 删除订单
     *
     * @param ids 订单id集合
     * @return
     */
    ResApi<String> deleteOrder(List<Long> ids);
}
