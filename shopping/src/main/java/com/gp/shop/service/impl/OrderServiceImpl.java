package com.gp.shop.service.impl;

import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Order;
import com.gp.shop.model.entity.Page;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.OrderMapper;
import com.gp.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单
     *
     * @param request  请求
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryOrder(HttpServletRequest request, Integer pageNo, Integer pageSize) {
        Check.isNull(pageNo, "pageNo 参数为空");
        Check.isNull(pageSize, "pageSize 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 查询数量
        Long total = orderMapper.count(accountId);
        // 分页
        Page page = new Page(pageNo, pageSize, total);
        // 查询订单
        List<Order> orders = orderMapper.queryByList(accountId, page);

        Map<String, Object> map = new HashMap<>();
        map.put("orders", orders);
        map.put("page", page);
        return new ResApi<>(map);
    }

    /**
     * 删除订单
     *
     * @param ids 订单id集合
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> deleteOrder(List<Long> ids) {
        Check.isNull(ids, "ids 参数为空");
        // 删除订单
        orderMapper.delete(ids);

        return new ResApi<>();
    }
}
