package com.gp.shop.controller;

import cn.hutool.core.util.IdUtil;
import com.gp.shop.common.utils.Check;
import com.gp.shop.model.entity.ResApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class UploadController {
    @Value("${img.path}")
    private String path;
    @Value("${img.host}")
    private String host;

    /**
     * 上传
     *
     * @param file 文件
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResApi<Map<String, Object>> uploadFile(@RequestPart("file") MultipartFile file) {
        Check.isNull(file, "file 参数为空");
        Map<String, Object> map = new HashMap<>();
        // 文件名
        String name = file.getOriginalFilename();
        if (name != null) {
            // 下标
            int index = name.indexOf(".");
            // 获取后缀
            String suffix = file.getOriginalFilename().substring(index);
            // 拼接文件名
            name = IdUtil.objectId() + suffix;
            // 上传封面
            try {
                file.transferTo(new File(path + name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put("img", host + name);
        }
        return new ResApi<>(map);
    }
}