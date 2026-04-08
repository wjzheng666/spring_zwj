package com.example.week05.controller;

import com.example.week05.common.Result;
import com.example.week05.entity.User;
import com.example.week05.service.UserTxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/user/tx")
@RequiredArgsConstructor
public class UserTxController {

    private final UserTxService userTxService;

    @PostMapping("/addTwo")
    public Result<String> addTwo(@RequestBody Map<String, User> map) {
        // 1. 先校验参数是否存在
        if (!map.containsKey("user1") || !map.containsKey("user2")) {
            return Result.error("请求体必须包含 user1 和 user2");
        }

        User user1 = map.get("user1");
        User user2 = map.get("user2");

        // 2. 再校验对象是否为 null
        if (user1 == null || user2 == null) {
            return Result.error("user1 和 user2 不能为 null");
        }

        userTxService.addTwoUsers(user1, user2);
        return Result.success("新增两个用户成功");
    }
}