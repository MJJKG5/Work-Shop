package com.gp.shop.service.impl;

import com.gp.shop.common.utils.Check;
import com.gp.shop.model.Commodity;
import com.gp.shop.model.entity.Page;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.CommodityMapper;
import com.gp.shop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
    @Value("${img.path}")
    private String path;
    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 查询商品
     *
     * @param storeId  商店id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryCommodity(Long storeId, Integer pageNo, Integer pageSize) {
        Check.isNull(pageNo, "pageNo 参数为空");
        Check.isNull(pageNo, "pageSize 参数为空");
        // 查询数量
        Long total = commodityMapper.count(storeId);
        // 分页
        Page page = new Page(pageNo, pageSize, total);
        // 查询商品
        List<Commodity> commodities = commodityMapper.queryByList(storeId, page);

        Map<String, Object> map = new HashMap<>();
        map.put("commodities", commodities);
        map.put("page", page);
        return new ResApi<>(map);
    }

    @Override
    public ResApi<Map<String, Object>> queryCommodity(Long id) {
        Check.isNull(id, "id 参数为空");
        // 查询商品
        Commodity commodity = commodityMapper.queryById(id);

        Map<String, Object> map = new HashMap<>();
        map.put("commodity", commodity);
        return new ResApi<>(map);
    }

    /**
     * 添加商品
     *
     * @param commodity 商品
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> addCommodity(Commodity commodity) {
        Check.isNull(commodity.getName(), "name 参数为空");
        Check.isNull(commodity.getInventory(), "inventory 参数为空");
        Check.isNull(commodity.getPrice(), "price 参数为空");
        Check.isNull(commodity.getUrl(), "url 参数为空");
        Check.isNull(commodity.getStoreId(), "storeId 参数为空");
        // 添加商品
        commodityMapper.add(commodity);

        return new ResApi<>();
    }

    /**
     * 修改商品
     *
     * @param commodity 商品
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> updateCommodity(Commodity commodity) {
        Check.isNull(commodity.getId(), "id 参数为空");
        Check.isNull(commodity.getName(), "name 参数为空");
        Check.isNull(commodity.getStatus(), "status 参数为空");
        Check.isNull(commodity.getInventory(), "inventory 参数为空");
        Check.isNull(commodity.getPrice(), "price 参数为空");
        Check.isNull(commodity.getUrl(), "url 参数为空");
        // 修改商品
        commodityMapper.update(commodity);

        return new ResApi<>();
    }

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> deleteCommodity(Long id) {
        Check.isNull(id, "id 参数为空");
        // 查询商品
        Commodity commodity = commodityMapper.queryById(id);
        if (commodity != null) {
            // 删除商品
            commodityMapper.delete(id);

            // 获取存储路径
            String img = commodity.getUrl();
            int index = img.lastIndexOf("/") + 1;
            img = img.substring(index);
            // 删除封面
            File file = new File(path + img);
            file.delete();
        }

        return new ResApi<>();
    }
}
