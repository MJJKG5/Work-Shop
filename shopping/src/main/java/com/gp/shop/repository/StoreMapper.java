package com.gp.shop.repository;

import com.gp.shop.model.Store;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreMapper {
    /**
     * 查询
     *
     * @param page 分页
     * @return
     */
    List<Store> queryByList(@Param("name") String name, @Param("page") Page page);

    /**
     * 添加
     *
     * @param store 商店
     */
    void add(@Param("store") Store store);

    /**
     * 修改
     *
     * @param store 商店
     */
    void update(@Param("store") Store store);

    /**
     * 删除
     *
     * @param id 商店id
     */
    void delete(Long id);

    /**
     * 查询数量
     *
     * @param name 商店名
     * @return
     */
    Long count(String name);
}
