package com.wenhua.community.dao;

/*
 * @Author:ChangBins
 * @Data:2022-09-11  15:19
 * @Description:community-com.wenhua.community.dao
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginTicketMapper {

    @Insert({"insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    /**
     * 更新登录状态
     * @param ticket 登录凭证
     * @param status 登录状态，“0”表示登录，“1”表示非登录
     */
    @Update({
            "update login_ticket set status=#{status} where ticket=#{ticket}"
    })
    int updateStatus(String ticket,int status);

}
