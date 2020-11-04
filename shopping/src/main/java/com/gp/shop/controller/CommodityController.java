package com.gp.shop.controller;

import com.gp.shop.model.Commodity;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    /**
     * 查询商品
     *
     * @param storeId  商店id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "commodity", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryCommodity(@RequestParam(required = false) Long storeId,
                                                      @RequestParam Integer pageNo,
                                                      @RequestParam Integer pageSize) {
        return commodityService.queryCommodity(storeId, pageNo, pageSize);
    }

    /**
     * 查询商品
     *
     * @param id 商品id
     * @return
     */
    @RequestMapping(value = "commodity/{id}", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryCommodity(@PathVariable Long id) {
        return commodityService.queryCommodity(id);
    }


    /**
     * 添加商品
     *
     * @param commodity 商品
     * @return
     */
    @RequestMapping(value = "commodity", method = RequestMethod.POST)
    public ResApi<String> addCommodity(@RequestBody Commodity commodity) {
        return commodityService.addCommodity(commodity);
    }

    /**
     * 修改商品
     *
     * @param commodity 商品
     * @return
     */
    @RequestMapping(value = "commodity", method = RequestMethod.PUT)
    public ResApi<String> updateCommodity(@RequestBody Commodity commodity) {
        return commodityService.updateCommodity(commodity);
    }

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return
     */
    @RequestMapping(value = "commodity", method = RequestMethod.DELETE)
    public ResApi<String> deleteCommodity(@RequestParam Long id) {
        return commodityService.deleteCommodity(id);
    }
}
