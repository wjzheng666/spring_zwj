package com.example.week05.service;

import com.example.week05.entity.User;
import java.util.List;

public interface UserService {
    // 新增
    int insert(User user);
    // 根据ID查询
    User getById(Long id);
    // 查询所有
    List<User> list();
    // 更新
    int updateById(User user);
    // 删除
    int deleteById(Long id);
}