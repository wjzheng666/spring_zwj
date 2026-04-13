package com.example.zhihu.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhihu.api.common.Result;
import com.example.zhihu.api.entity.Special;
import com.example.zhihu.api.service.SpecialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 专栏控制器
 */
@Tag(name = "专栏管理", description = "专栏相关接口")
@RestController
@RequestMapping("/api/v1/special")
public class SpecialController {
    @Resource
    private SpecialService specialService;
    
    /**
     * 分页查询专栏列表
     */
    @Operation(summary = "分页查询专栏", description = "根据标题模糊查询专栏列表")
    @GetMapping("/page")
    public Result<Page<Special>> list(
            @Parameter(description = "标题关键词") 
            @RequestParam(required = false) String title,
            @Parameter(description = "页码，从1开始", example = "1") 
            @RequestParam(defaultValue = "1") int pageNum,
            @Parameter(description = "每页条数", example = "10") 
            @RequestParam(defaultValue = "10") int pageSize) {
        
        Page<Special> page = specialService.selectByTitle(title, pageNum, pageSize);
        return Result.success(page);
    }
    
    /**
     * 健康检查接口
     */
    @Operation(summary = "健康检查", description = "检查服务是否正常运行")
    @GetMapping("/health")
    public Result<String> health() {
        return Result.success("服务正常运行");
    }
    
    /**
     * 根据ID查询专栏详情
     */
    @Operation(summary = "查询专栏详情", description = "根据专栏ID查询详细信息")
    @GetMapping("/detail")
    public Result<Special> getById(@RequestParam Long id) {
        
        Special special = specialService.getById(id);
        if (special == null) {
            return Result.error("专栏不存在");
        }
        return Result.success(special);
    }
}
