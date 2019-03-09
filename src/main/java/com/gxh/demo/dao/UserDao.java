package com.gxh.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxh.demo.entity.User;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
  public List<User> getUser();
}
