package com.gp.shop.service;

import com.gp.shop.model.Address;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AddressService {
    /**
     * 查询地址
     *
     * @param request 请求
     * @return
     */
    ResApi<Map<String, Object>> queryAddress(HttpServletRequest request);

    /**
     * 添加地址
     *
     * @param address 地址
     * @param request 请求
     * @return
     */
    ResApi<String> addAddress(Address address, HttpServletRequest request);

    /**
     * 修改地址
     *
     * @param address 地址
     * @return
     */
    ResApi<String> updateAddress(Address address);

    /**
     * 删除地址
     *
     * @param id 地址id
     * @return
     */
    ResApi<String> deleteAddress(Long id);
}
