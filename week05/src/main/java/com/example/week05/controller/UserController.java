package com.example.week05.controller;

import com.example.week05.common.Result;
import com.example.week05.entity.User;
import com.example.week05.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 新增用户
    @PostMapping
    public Result<String> add(@RequestBody User user) {
        int row = userService.insert(user);
        if (row != 1) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    // 查询所有用户
    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    // 更新用户
    @PutMapping
    public Result<String> update(@RequestBody User user) {
        int row = userService.updateById(user);
        if (row != 1) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        int row = userService.deleteById(id);
        if (row != 1) {
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}