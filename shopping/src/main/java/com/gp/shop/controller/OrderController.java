package com.gp.shop.controller;

import com.gp.shop.model.Order;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询订单
     *
     * @param request  请求
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryOrder(HttpServletRequest request,
                                                  @RequestParam Integer pageNo,
                                                  @RequestParam Integer pageSize) {
        return orderService.queryOrder(request, pageNo, pageSize);
    }

    /**
     * 删除订单
     *
     * @param ids 订单id集合
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.DELETE)
    public ResApi<String> deleteOrder(@RequestParam List<Long> ids) {
        return orderService.deleteOrder(ids);
    }
}
