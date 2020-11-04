package com.gp.shop.service;

import com.gp.shop.model.Store;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface StoreService {
    /**
     * 查询商店
     *
     * @param name     商店名
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    ResApi<Map<String, Object>> queryStore(String name, Integer pageNo, Integer pageSize);

    /**
     * 添加商店
     *
     * @param store   商店
     * @return
     */
    ResApi<String> addStore(Store store);

    /**
     * 修改商店
     *
     * @param store 商店
     * @return
     */
    ResApi<String> updateStore(Store store);

    /**
     * 删除商店
     *
     * @param id 商店id
     * @return
     */
    ResApi<String> deleteStore(Long id);
}
