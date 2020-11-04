package com.gp.shop.controller;

import com.gp.shop.model.Address;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 查询地址
     *
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "address", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryAddress(HttpServletRequest request) {
        return addressService.queryAddress(request);
    }

    /**
     * 添加地址
     *
     * @param address 地址
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "address", method = RequestMethod.POST)
    public ResApi<String> addAddress(@RequestBody Address address, HttpServletRequest request) {
        return addressService.addAddress(address, request);
    }

    /**
     * 修改地址
     *
     * @param address 地址
     * @return
     */
    @RequestMapping(value = "address", method = RequestMethod.PUT)
    public ResApi<String> updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    /**
     * 删除地址
     *
     * @param id 地址id
     * @return
     */
    @RequestMapping(value = "address", method = RequestMethod.DELETE)
    public ResApi<String> deleteAddress(@RequestParam Long id) {
        return addressService.deleteAddress(id);
    }
}
