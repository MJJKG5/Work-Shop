package com.gp.shop.service.impl;

import cn.hutool.core.convert.Convert;
import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Address;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.AddressMapper;
import com.gp.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询地址
     *
     * @param request 请求
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryAddress(HttpServletRequest request) {
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 查询地址
        List<Address> addresses = addressMapper.queryByAccountId(accountId);

        Map<String, Object> map = new HashMap<>();
        map.put("addresses", addresses);
        return new ResApi<>(map);
    }

    /**
     * 添加地址
     *
     * @param address 地址
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> addAddress(Address address, HttpServletRequest request) {
        Check.isNull(address.getTo(), "to 参数为空");
        Check.isNull(address.getPhone(), "phone 参数为空");
        Check.isNull(address.getAddress(), "address 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 设置账户id
        address.setAccountId(accountId);
        // 添加地址
        addressMapper.add(address);

        return new ResApi<>();
    }

    /**
     * 修改地址
     *
     * @param address 地址
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> updateAddress(Address address) {
        Check.isNull(address.getId(), "id 参数为空");
        Check.isNull(address.getTo(), "to 参数为空");
        Check.isNull(address.getPhone(), "phone 参数为空");
        Check.isNull(address.getAddress(), "address 参数为空");
        // 修改地址
        addressMapper.update(address);

        return new ResApi<>();
    }

    /**
     * 删除地址
     *
     * @param id 地址id
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> deleteAddress(Long id) {
        Check.isNull(id, "id 参数为空");
        // 删除地址
        addressMapper.delete(id);

        return new ResApi<>();
    }
}
