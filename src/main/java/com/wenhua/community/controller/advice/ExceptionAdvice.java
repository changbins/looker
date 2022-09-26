package com.wenhua.community.controller.advice;

/*
 * @Author:ChangBins
 * @Data:2022-09-23  15:27
 * @Description:community-com.wenhua.community.controller.advice
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.entity.Page;
import com.wenhua.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception exception, HttpServletResponse Response, HttpServletRequest Request) throws IOException {
        LOGGER.error("服务器发生异常" + exception.getMessage());
        for (StackTraceElement element : exception.getStackTrace()) {
            LOGGER.error(element.toString());
        }

        String xRequestedWith = Request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            Response.setContentType("application/plain;character=utf-8");
            PrintWriter writer = Response.getWriter();
            writer.write(CommunityUtil.getJsonString(1, "服务器异常"));
        } else {
            Response.sendRedirect(Request.getContextPath() + "/error");
        }
    }
}
