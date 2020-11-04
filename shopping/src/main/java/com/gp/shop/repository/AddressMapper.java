package com.gp.shop.repository;

import com.gp.shop.model.Address;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {
    /**
     * 查询
     *
     * @param accountId 账户id
     * @return
     */
    List<Address> queryByAccountId(Long accountId);

    /**
     * 添加
     *
     * @param address 地址
     */
    void add(@Param("address") Address address);

    /**
     * 修改
     *
     * @param address 地址
     */
    void update(@Param("address") Address address);

    /**
     * 删除
     *
     * @param id 地址id
     */
    void delete(Long id);

    /**
     * 查询数量
     *
     * @return
     */
    Long count();
}
