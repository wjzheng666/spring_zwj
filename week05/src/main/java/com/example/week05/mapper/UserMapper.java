package com.example.week05.mapper;

import com.example.week05.entity.User;
import org.apache.ibatis.annotations.Mapper; // 关键：导入Mapper注解
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectList();
    User selectById(Long id);
    int insert(User user);
    int updateById(User user);
    int deleteById(Long id);
    List<User> selectByCondition(User user);
}