package com.gp.shop.common.utils;

import com.gp.shop.common.exception.NullParamException;

public class Check {
    /**
     * 是否空值
     *
     * @param value 值
     * @param msg   提示信息
     */
    public static void isNull(Object value, String msg) {
        if (null == value || "".equals(value)) {
            throw new NullParamException(msg);
        }
    }
}
