package com.gp.shop.service.impl;

import com.gp.shop.common.exception.LogicException;
import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Commodity;
import com.gp.shop.model.ShopCart;
import com.gp.shop.model.entity.Page;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.CommodityMapper;
import com.gp.shop.repository.ShopCartMapper;
import com.gp.shop.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shopCartService")
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 查询购物车
     *
     * @param request  请求
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryShopCart(HttpServletRequest request, Integer pageNo, Integer pageSize) {
        Check.isNull(pageNo, "pageNo 参数为空");
        Check.isNull(pageNo, "pageSize 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 查询数量
        Long total = shopCartMapper.count(accountId);
        // 分页
        Page page = new Page(pageNo, pageSize, total);
        // 查询购物车中的商品
        List<ShopCart> shopCarts = shopCartMapper.queryByList(accountId, page);

        Map<String, Object> map = new HashMap<>();
        map.put("shopCarts", shopCarts);
        map.put("page", page);
        return new ResApi<>(map);
    }

    /**
     * 添加购物车
     *
     * @param shopCart 购物车
     * @param request  请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> addShopCart(ShopCart shopCart, HttpServletRequest request) {
        Check.isNull(shopCart.getCommId(), "commId 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();

        // 查询商品
        Commodity commodity = commodityMapper.queryById(shopCart.getCommId());
        // 验证商品
        if (commodity == null) {
            throw new LogicException("商品不存在");
        }

        // 验证购物车中的商品
        ShopCart result = shopCartMapper.queryByAccountIdAndCommId(accountId, shopCart.getCommId());
        if (null == result) {
            // 验证库存
            if (commodity.getInventory() < 1) {
                throw new LogicException("商品库存不足");
            }

            // 设置数量
            shopCart.setNumber(1);
            // 设置账户id
            shopCart.setAccountId(accountId);
            // 购物车中添加商品
            shopCartMapper.add(shopCart);
        } else {
            // 验证库存
            if (commodity.getInventory() < result.getNumber() + 1) {
                throw new LogicException("商品库存不足");
            }

            // 添加商品
            ShopCart cart = new ShopCart();
            // 商品id
            cart.setCommId(shopCart.getCommId());
            // 数量
            cart.setNumber(result.getNumber() + 1);
            // 账户id
            cart.setAccountId(accountId);
            // 购物车中添加商品数量
            shopCartMapper.update(cart);
        }
        return new ResApi<>();
    }

    /**
     * 修改购物车
     *
     * @param shopCart 购物车
     * @param request  请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> updateShopCart(ShopCart shopCart, HttpServletRequest request) {
        Check.isNull(shopCart.getCommId(), "commId 参数为空");
        Check.isNull(shopCart.getNumber(), "number 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();

        // 查询商品
        Commodity commodity = commodityMapper.queryById(shopCart.getCommId());
        // 验证商品
        if (commodity == null) {
            throw new LogicException("商品不存在");
        }
        // 验证库
        if (commodity.getInventory() < shopCart.getNumber()) {
            throw new LogicException("商品库存不足");
        }

        // 设置账户id
        shopCart.setAccountId(accountId);
        // 修改购物车中的商品
        shopCartMapper.update(shopCart);

        return new ResApi<>();
    }

    /**
     * 删除购物车
     *
     * @param cmmId   商品id
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> deleteShopCart(Long cmmId, HttpServletRequest request) {
        Check.isNull(cmmId, "commId 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 删除购物车中的商品
        shopCartMapper.deleteByAccountIdAndCommId(accountId, cmmId);

        return new ResApi<>();
    }
}
