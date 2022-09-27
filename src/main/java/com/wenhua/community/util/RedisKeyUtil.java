package com.wenhua.community.util;

/*
 * @Author:ChangBins
 * @Data:2022-09-26  15:45
 * @Description:community-com.wenhua.community.util
 * @Version：1.0
 * @Detail：
 * */
public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";

    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }
}
