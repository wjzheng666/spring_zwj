package com.example.week05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.week05.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMPMapper extends BaseMapper<User> {
    // BaseMapper已提供所有CRUD方法，无需编写
}