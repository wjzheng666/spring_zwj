package com.example.zhihu.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_special")
public class Special {
    @Schema(description = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "封面")
    private String banner;

    @Schema(description = "描述")
    private String introduction;

    @Schema(description = "是否关注")
    private Boolean isFollowing;

    @Schema(description = "关注数量")
    private Integer followersCount;

    @Schema(description = "浏览数量")
    private Integer viewCount;

    @Schema(description = "更新时间")
    private Long updated;
}
