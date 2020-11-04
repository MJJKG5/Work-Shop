package com.gp.shop.service.impl;

import com.gp.shop.common.utils.Check;
import com.gp.shop.common.utils.Session;
import com.gp.shop.model.Reviews;
import com.gp.shop.model.entity.Page;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.repository.OrderMapper;
import com.gp.shop.repository.ReviewsMapper;
import com.gp.shop.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("reviewsService")
public class ReviewsServiceImpl implements ReviewsService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ReviewsMapper reviewsMapper;

    /**
     * 查询评论
     *
     * @param commId   商品id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @Override
    public ResApi<Map<String, Object>> queryReviews(Long commId, Integer pageNo, Integer pageSize) {
        Check.isNull(commId, "commId 参数为空");
        Check.isNull(pageNo, "pageNo 参数为空");
        Check.isNull(pageSize, "pageSize 参数为空");
        // 查询数量
        Long total = reviewsMapper.count(commId);
        // 分页
        Page page = new Page(pageNo, pageSize, total);
        // 查询订单
        List<Reviews> reviews = reviewsMapper.queryByList(commId, page);

        Map<String, Object> map = new HashMap<>();
        map.put("reviews", reviews);
        map.put("page", page);
        return new ResApi<>(map);
    }

    /**
     * 添加评论
     *
     * @param reviews 评论
     * @param request 请求
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> addReviews(Reviews reviews, HttpServletRequest request) {
        Check.isNull(reviews.getCommId(), "commId 参数为空");
        Check.isNull(reviews.getOrderId(), "orderId 参数为空");
        Check.isNull(reviews.getMessage(), "message 参数为空");
        // 获取账户id
        Long accountId = new Session(request).accountId();
        // 设置账户id
        reviews.setAccountId(accountId);
        // 添加评论
        reviewsMapper.add(reviews);
        // 修改订单评论状态
        orderMapper.updateById(reviews.getOrderId(), "1");

        return new ResApi<>();
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return
     */
    @Transactional
    @Override
    public ResApi<String> deleteReviews(Long id) {
        Check.isNull(id, "id 参数为空");
        // 删除评论
        reviewsMapper.delete(id);

        return new ResApi<>();
    }
}
