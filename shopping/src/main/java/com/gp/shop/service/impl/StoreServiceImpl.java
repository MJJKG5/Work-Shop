package com.gp.shop.service.impl;

import com.gp.shop.common.utils.Check;
import com.gp.shop.model.Store;
import com.gp.shop.model.entity.Page;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.StoreMapper;
import com.gp.shop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    /**
     * 查询商店
     *
     * @param name     商店名
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryStore(String name, Integer pageNo, Integer pageSize) {
        Check.isNull(pageNo, "pageNo 参数为空");
        Check.isNull(pageSize, "pageSize 参数为空");
        // 查询数量
        Long total = storeMapper.count(name);
        // 分页
        Page page = new Page(pageNo, pageSize, total);
        // 查询商店
        List<Store> stores = storeMapper.queryByList(name, page);

        Map<String, Object> map = new HashMap<>();
        map.put("stores", stores);
        map.put("page", page);
        return new ResApi<>(map);
    }

    /**
     * 添加商店
     *
     * @param store 商店
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> addStore(Store store) {
        Check.isNull(store.getName(), "name 参数为空");
        // 添加商店
        storeMapper.add(store);

        return new ResApi<>();
    }

    /**
     * 修改商店
     *
     * @param store 商店
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> updateStore(Store store) {
        Check.isNull(store.getId(), "id 参数为空");
        Check.isNull(store.getName(), "name 参数为空");
        // 修改影院
        storeMapper.update(store);

        return new ResApi<>();
    }

    /**
     * 删除商店
     *
     * @param id 商店id
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> deleteStore(Long id) {
        Check.isNull(id, "id 参数为空");
        // 删除影院
        storeMapper.delete(id);

        return new ResApi<>();
    }
}
