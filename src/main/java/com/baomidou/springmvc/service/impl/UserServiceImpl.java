package com.baomidou.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.ServiceImpl;
import com.baomidou.springmvc.mapper.UserMapper;
import com.baomidou.springmvc.model.User;
import com.baomidou.springmvc.service.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}

