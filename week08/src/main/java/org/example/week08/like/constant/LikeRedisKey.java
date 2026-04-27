package org.example.week08.like.constant;

public interface LikeRedisKey {
    // 文章点赞用户集合（key: like:video:{videoId}）
    String LIKE_VIDEO_USERS = "like:video:%s";
    // 文章点赞数统计Hash（key: like:video:count）
    String LIKE_VIDEO_COUNT = "like:video:count";
    // 文章点赞排行ZSet（key: like:video:rank）
    String LIKE_VIDEO_RANK = "like:video:rank";
    // 点赞用户集合过期时间（7天）
    long EXPIRE_DAYS = 7;
}