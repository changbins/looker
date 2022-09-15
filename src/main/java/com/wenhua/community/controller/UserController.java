package com.wenhua.community.controller;

/*
 * @Author:ChangBins
 * @Data:2022-09-12  16:03
 * @Description:community-com.wenhua.community.controller
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.annotation.LoginRequired;
import com.wenhua.community.entity.User;
import com.wenhua.community.service.UserService;
import com.wenhua.community.util.CommunityUtil;
import com.wenhua.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 跳转到设置页面
     *
     * @return 返回到跳转页面
     */
    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String setting() {
        return "/site/setting";
    }


    /**
     * 上传头像
     *
     * @param headerImage 头像文件
     * @param model       模板数据存储
     * @return 返回到首页
     */
    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        //判断用户是否选择文件
        if (headerImage == null) {
            model.addAttribute("error", "您还没有选择图片！");
            return "/site/setting";
        }
        //截图图片的后缀
        String filename = headerImage.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "文件的格式不正确！");
            return "/site/setting";
        }
        //生成随机的文件名
        filename = CommunityUtil.generateUUID() + suffix;
        //确定文件的存放路径
        File dest = new File(uploadPath + "/" + filename);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            LOGGER.error("上传共享文件失败：" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常" + e);
        }
        //更新当前用户的路径（更新web访问路径）
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header" + filename;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }

    /**
     * 查看头像
     *
     * @param filename 路径上携带的文件名
     * @param response 应答服务器请求
     */
    @RequestMapping(path = "/header/{filename}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("filename") String filename, HttpServletResponse response) {
        //服务器上存放的路径
        filename = uploadPath + "/" + filename;
        //文件的后缀解析
        String suffix = filename.substring(filename.lastIndexOf("."));
        //响应图片
        response.setContentType("image/" + suffix);
        try (
                FileInputStream stream = new FileInputStream(filename);
                OutputStream os = response.getOutputStream();
        ) {
            //建立缓冲区域，成批次的输出到缓冲区域
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = stream.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            LOGGER.error("读取头像失败" + e.getMessage());
        }
    }
    //TODO:这里还有一个bug没修复，头像上传后无法显示，debug后图片上传成功且数据库底层已修改为新头像
    //TODO:修改密码未实现
}
