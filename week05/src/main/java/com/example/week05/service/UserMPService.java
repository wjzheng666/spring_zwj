package com.example.week05.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.week05.entity.User;
import com.example.week05.mapper.UserMPMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMPService {

    private final UserMPMapper userMPMapper;

    public int add(User user) {
        return userMPMapper.insert(user);
    }

    public int delete(Long id) {
        return userMPMapper.deleteById(id);
    }

    public int update(User user) {
        return userMPMapper.updateById(user);
    }

    public User getById(Long id) {
        return userMPMapper.selectById(id);
    }

    public List<User> list() {
        return userMPMapper.selectList(null);
    }

    public Page<User> page(String username, Integer pageNum, Integer pageSize) {
        Page<User> page = Page.of(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(username != null && !username.isEmpty(), User::getUsername, username);
        return userMPMapper.selectPage(page, wrapper);
    }
}