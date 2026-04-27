package org.example.week08.like.controller;

import org.example.week08.common.dto.ApiResult;
import org.example.week08.like.dto.VideoLikeRankDTO;
import org.example.week08.like.service.VideoLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class VideoLikeController {

    private final VideoLikeService videoLikeService;

    /**
     * 点赞/取消点赞
     */
    @PostMapping("/toggle")
    public ApiResult<String> toggleLike(
            @RequestParam String articleId,
            @RequestParam String userId) {
        boolean isLike = videoLikeService.toggleLike(articleId, userId);
        String msg = isLike ? "点赞成功" : "取消点赞成功";
        return ApiResult.success(msg);
    }

    /**
     * 获取文章点赞数
     */
    @GetMapping("/count")
    public ApiResult<Long> getLikeCount(@RequestParam String articleId) {
        return ApiResult.success(videoLikeService.getLikeCount(articleId));
    }

    /**
     * 获取热门文章排行（默认前10）
     */
    @GetMapping("/rank")
    public ApiResult<List<VideoLikeRankDTO>> getArticleRank(
            @RequestParam(defaultValue = "10") int topN) {
        Set<ZSetOperations.TypedTuple<String>> tuples = videoLikeService.getTopNArticleRank(topN);
        List<VideoLikeRankDTO> rankList = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            if (tuple.getValue() != null && tuple.getScore() != null) {
                rankList.add(new VideoLikeRankDTO(tuple.getValue(), tuple.getScore().longValue()));
            }
        }
        return ApiResult.success(rankList);
    }

    /**
     * 检查用户是否已点赞
     */
    @GetMapping("/check")
    public ApiResult<Boolean> checkLiked(
            @RequestParam String articleId,
            @RequestParam String userId) {
        return ApiResult.success(videoLikeService.isUserLiked(articleId, userId));
    }
}