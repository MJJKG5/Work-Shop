package com.gp.shop.repository;

import com.gp.shop.model.Reviews;
import com.gp.shop.model.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsMapper {
    /**
     * 查询
     *
     * @param commId 商品id
     * @param page   分页
     * @return
     */
    List<Reviews> queryByList(@Param("commId") Long commId, @Param("page") Page page);

    /**
     * 添加
     *
     * @param reviews 评论
     */
    void add(@Param("reviews") Reviews reviews);

    /**
     * 删除
     *
     * @param id 评论id
     */
    void delete(Long id);

    /**
     * 查询数量
     *
     * @param commId 商品id
     * @return
     */
    Long count(Long commId);
}
