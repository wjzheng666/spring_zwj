package org.example.week08.like.service;

import org.example.week08.like.constant.LikeRedisKey;
import org.example.week08.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VideoLikeService {

    private final RedisUtil redisUtil;

    /**
     * 点赞/取消点赞（幂等）
     * @param videoId 文章ID
     * @param userId 用户ID
     * @return true=点赞成功 false=取消点赞成功
     */
    public boolean toggleLike(String videoId, String userId) {
        String userKey = String.format(LikeRedisKey.LIKE_VIDEO_USERS, videoId);

        // 1. 判断用户是否已点赞
        Boolean isLiked = redisUtil.isMember(userKey, userId);
        if (Boolean.TRUE.equals(isLiked)) {
            // 2. 取消点赞
            redisUtil.removeFromSet(userKey, userId);
            // 3. 点赞数-1
            redisUtil.increment(LikeRedisKey.LIKE_VIDEO_COUNT, videoId, -1);
        } else {
            // 4. 新增点赞
            redisUtil.addToSet(userKey, userId);
            // 5. 点赞数+1
            redisUtil.increment(LikeRedisKey.LIKE_VIDEO_COUNT, videoId, 1);
            // 6. 设置用户集合过期时间
            redisUtil.expire(userKey, LikeRedisKey.EXPIRE_DAYS, TimeUnit.DAYS);
        }

        // 7. 更新排行ZSet
        Long newCount = getLikeCount(videoId);
        redisUtil.addToZSet(LikeRedisKey.LIKE_VIDEO_RANK, videoId, newCount);

        return !Boolean.TRUE.equals(isLiked);
    }

    /**
     * 获取文章点赞数
     */
    public Long getLikeCount(String articleId) {
        Object count = redisUtil.hget(LikeRedisKey.LIKE_VIDEO_COUNT, articleId);
        return count == null ? 0 : Long.parseLong(count.toString());
    }

    /**
     * 获取热门文章排行（前N）
     */
    public Set<ZSetOperations.TypedTuple<String>> getTopNArticleRank(int topN) {
        return redisUtil.zReverseRangeWithScores(LikeRedisKey.LIKE_VIDEO_RANK, 0, topN - 1);
    }


    /**
     * 判断用户是否已点赞
     */
    public boolean isUserLiked(String videoId, String userId) {
        String userKey = String.format(LikeRedisKey.LIKE_VIDEO_USERS, videoId);
        return Boolean.TRUE.equals(redisUtil.isMember(userKey, userId));
    }
}