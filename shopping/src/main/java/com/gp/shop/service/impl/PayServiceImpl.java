package com.gp.shop.service.impl;

import com.gp.shop.common.exception.LogicException;
import com.gp.shop.common.exception.NullParamException;
import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Commodity;
import com.gp.shop.model.Order;
import com.gp.shop.model.Pay;
import com.gp.shop.model.ShopCart;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.CommodityMapper;
import com.gp.shop.repository.OrderMapper;
import com.gp.shop.repository.ShopCartMapper;
import com.gp.shop.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("payService")
public class PayServiceImpl implements PayService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 单商品支付
     *
     * @param pay     支付
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> pay(Pay pay, HttpServletRequest request) {
        Check.isNull(pay.getCommId(), "commId 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 查询商品
        Commodity commodity = commodityMapper.queryById(pay.getCommId());
        // 验证商品
        if (null == commodity || "0".equals(commodity.getStatus())) {
            throw new LogicException("商品不存在或已下架");
        }
        if (commodity.getInventory() < 1) {
            throw new LogicException("商品库存不足");
        }

        // 获取订单实例
        Order order = getOrderInstance(commodity, 1, commodity.getPrice(), accountId);
        // 新增订单
        orderMapper.add(order);

        // 修改商品库存
        commodity.setInventory(commodity.getInventory() - 1);
        commodityMapper.update(commodity);

        return new ResApi<>();
    }

    /**
     * 购物车商品支付
     *
     * @param pay     支付
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> pays(Pay pay, HttpServletRequest request) {
        if (pay == null || pay.getCommIds() == null || pay.getCommIds().isEmpty()) {
            throw new NullParamException("commIds 参数为空");
        }
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 查询购物车
        List<ShopCart> shopCarts = shopCartMapper.queryByAccountId(accountId);
        // 查询商品
        List<Commodity> commodities = commodityMapper.queryByIds(pay.getCommIds());

        // 订单集合
        List<Order> orders = new ArrayList<>();

        for (Long commId : pay.getCommIds()) {
            // 获取购物车商品
            Optional<ShopCart> shopCart = shopCarts.stream().filter(cart -> commId.equals(cart.getCommId())).findFirst();
            // 获取商品列表中的商品
            Optional<Commodity> commodity = commodities.stream().filter(comm -> commId.equals(comm.getId())).findFirst();
            // 验证商品
            if (shopCart.isEmpty() || commodity.isEmpty()) {
                throw new LogicException("商品不存在或已下架");
            }
            if (commodity.get().getInventory() < shopCart.get().getNumber()) {
                throw new LogicException(commodity.get().getName() + "商品库存不足");
            }

            // 购买数量
            BigDecimal number = new BigDecimal(shopCart.get().getNumber());
            // 商品单价
            BigDecimal price = commodity.get().getPrice();
            // 结算总价
            BigDecimal totalPrice = number.multiply(price);

            // 获取订单实例
            Order order = getOrderInstance(commodity.get(), shopCart.get().getNumber(), totalPrice, accountId);
            // 存储订单实例
            orders.add(order);

            // 修改商品库存
            Commodity result = commodity.get();
            result.setInventory(result.getInventory() - number.intValue());
            commodityMapper.update(result);
        }
        // 添加订单
        orderMapper.adds(orders);

        // 删除购物车中已结算的商品
        shopCartMapper.deleteByCommIds(pay.getCommIds());

        return new ResApi<>();
    }

    /**
     * 获取订单实例
     *
     * @param commodity  商品
     * @param number     数量
     * @param totalPrice 总价
     * @param accountId  账户
     * @return
     */
    private Order getOrderInstance(Commodity commodity, Integer number, BigDecimal totalPrice, Long accountId) {
        // 创建订单
        Order order = new Order();
        // 商品id
        order.setCommId(commodity.getId());
        // 数量
        order.setNumber(number);
        // 单价
        order.setUnitPrice(commodity.getPrice());
        // 总价
        order.setTotalPrice(totalPrice);
        // 账户id
        order.setAccountId(accountId);
        return order;
    }
}
