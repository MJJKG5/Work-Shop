package com.gp.shop.controller;

import com.gp.shop.model.Reviews;
import com.gp.shop.model.entity.ResApi;
import com.gp.shop.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ReviewsController {
    @Autowired
    private ReviewsService reviewsService;

    /**
     * 查询评论
     *
     * @param commId   商品id
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return
     */
    @RequestMapping(value = "reviews", method = RequestMethod.GET)
    public ResApi<Map<String, Object>> queryReviews(@RequestParam Long commId,
                                                    @RequestParam Integer pageNo,
                                                    @RequestParam Integer pageSize) {
        return reviewsService.queryReviews(commId, pageNo, pageSize);
    }

    /**
     * 添加评论
     *
     * @param reviews 评论
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "reviews", method = RequestMethod.POST)
    public ResApi<String> addReviews(@RequestBody Reviews reviews, HttpServletRequest request) {
        return reviewsService.addReviews(reviews, request);
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return
     */
    @RequestMapping(value = "reviews", method = RequestMethod.DELETE)
    public ResApi<String> deleteReviews(@RequestParam Long id) {
        return reviewsService.deleteReviews(id);
    }
}
