package com.wenhua.community.dao;

/*
 * @Author:ChangBins
 * @Data:2022-09-21  9:24
 * @Description:community-com.wenhua.community.dao
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    /**
     * 查询当前用户的会话列表，针对每个会话只返回一条最新的私信
     *
     * @param userId 用户id
     * @param offset 每页起始数
     * @param limit  每页条数
     * @return 信息的集合
     */
    List<Message> selectConversations(int userId, int offset, int limit);

    /**
     * 查询当前用户的会话数量
     *
     * @param userId 用户id
     * @return 会话数量
     */
    int selectConversationCount(int userId);

    /**
     * 查询某个会话所包含的私信列表
     *
     * @param conversationId 会话的id
     * @param offset         分页起始值
     * @param limit          分页条数
     * @return 返回私信的集合
     */
    List<Message> selectLetters(String conversationId, int offset, int limit);

    /**
     * 查询未读私信的数量
     *
     * @param conversationId 会话id
     * @return 返回未读私信数量
     */
    int selectLettersCount(String conversationId);

    /**
     * 查询未读私信数量
     *
     * @param userId         用户id
     * @param conversationId 会话id
     * @return 私信数量
     */
    int selectLetterUnreadCount(int userId, String conversationId);

    /**
     * 新增消息
     *
     * @param message 新增的消息对象
     * @return 受影响的行数
     */
    int insertMessage(Message message);

    /**
     * 修改消息的状态
     * @param ids 发送消息的用户的id
     * @param status 改变的状态号
     * @return 受影响的条数
     */
    int updateStatus(List<Integer> ids,int status);


}
