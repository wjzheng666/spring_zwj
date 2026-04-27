package org.example.week08.like.dto;

// 文章点赞排行返回DTO
public record VideoLikeRankDTO(
        String videoId,
        Long likeCount
) {}