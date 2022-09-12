package com.wenhua.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @Author:ChangBins
 * @Data:2022-09-12  12:58
 * @Description:community-com.wenhua.community.util
 * @Version：1.0
 * @Detail：从request中取cookie
 * */
public class CookieUitl {
    public static String getValue(HttpServletRequest request, String name) {
        if (request == null || name == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies){
                if (c.getName().equals(name)) {
                    return c.getValue();
                }
            }
        }
        return  null;
    }
}
