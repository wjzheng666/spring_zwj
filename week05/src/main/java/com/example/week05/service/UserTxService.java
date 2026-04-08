package com.example.week05.service;

import com.example.week05.entity.User;
import com.example.week05.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserTxService {

    private final UserMapper userMapper;

    // 关键：加上 rollbackFor，确保所有异常都触发回滚
    @Transactional(rollbackFor = Exception.class)
    public void addTwoUsers(User user1, User user2) {
        // 第一步：新增用户1
        userMapper.insert(user1);

        // 模拟异常：如果用户2的用户名为空，抛出运行时异常，触发事务回滚
        if (user2.getUsername() == null || user2.getUsername().trim().isEmpty()) {
            throw new RuntimeException("用户2姓名不能为空，事务回滚");
        }

        // 第二步：新增用户2
        userMapper.insert(user2);
    }
}