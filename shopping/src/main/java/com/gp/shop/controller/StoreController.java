package com.gp.shop.controller;

import com.gp.shop.model.Store;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api")
public class StoreController {
    @Autowired
    private StoreService storeService;

    /**
     * 查询商店
     *
     * @param name     商店名
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "store", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryStore(@RequestParam(required = false) String name,
                                                  @RequestParam Integer pageNo,
                                                  @RequestParam Integer pageSize) {
        return storeService.queryStore(name, pageNo, pageSize);
    }

    /**
     * 添加商店
     *
     * @param store   商店
     * @return
     */
    @RequestMapping(value = "store", method = RequestMethod.POST)
    public ResApi<String> addStore(@RequestBody Store store) {
        return storeService.addStore(store);
    }

    /**
     * 修改商店
     *
     * @param store 商店
     * @return
     */
    @RequestMapping(value = "store", method = RequestMethod.PUT)
    public ResApi<String> updateStore(@RequestBody Store store) {
        return storeService.updateStore(store);
    }

    /**
     * 删除商店
     *
     * @param id 商店id
     * @return
     */
    @RequestMapping(value = "store", method = RequestMethod.DELETE)
    public ResApi<String> deleteStore(@RequestParam Long id) {
        return storeService.deleteStore(id);
    }
}
