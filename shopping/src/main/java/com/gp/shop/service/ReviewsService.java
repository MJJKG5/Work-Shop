package com.gp.shop.service;

import com.gp.shop.model.Reviews;
import com.gp.shop.model.entity.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ReviewsService {
    /**
     * 查询评论
     *
     * @param commId   商品id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    ResApi<Map<String, Object>> queryReviews(Long commId, Integer pageNo, Integer pageSize);

    /**
     * 添加评论
     *
     * @param reviews 评论
     * @param request 请求
     * @return
     */
    ResApi<String> addReviews(Reviews reviews, HttpServletRequest request);

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return
     */
    ResApi<String> deleteReviews(Long id);
}
